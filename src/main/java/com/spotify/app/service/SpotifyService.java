package com.spotify.app.service;

import com.spotify.app.dao.SpotifyUserDAO;
import com.spotify.app.models.searchitems.dto.Artist;
import com.spotify.app.models.searchitems.dto.Item;
import com.spotify.app.models.searchitems.dto.SearchItemDTO;
import com.spotify.app.models.SpotifyInfo.UserInfo;
import com.spotify.app.models.UserDB.SpotifyDBUser;
import com.spotify.app.models.accesstoken.response.AccessTokenResponse;
import com.spotify.app.models.searchitems.response.SearchItemResponse;
import com.spotify.app.models.user.request.RegisterUserRequest;
import com.spotify.app.models.user.response.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class SpotifyService {
    @Value("${searchItemURL}")
    public String searchItemURL;

    @Value("${client-id}")
    public String clientId;

    @Value("${client-secret}")
    public String clientSecret;

    @Value("${selectUsernameUrl}")
    public String selectUsernameUrl;

    @Value("${accessTokenURL}")
    public String accessTokenURL;

    @Value("${frontendHost}")
    public String frontendHost;

    @Value("${redirect-uri}")
    public String redirectURI;

    @Value("${oembedURI}")
    public String oembedURI;

    @Value("${createPlaylistURI}")
    public String createPlaylistURI;

    @Value("${userProfileURI}")
    public String userProfileURI;

    @Value("${playlistName}")
    public String playlistName;

    @Value("${addItemsToPlaylistURI}")
    public String addItemsToPlaylistURI;

    @Value("${generateLinkURL}")
    public String generateLinkURL;

    @Value("${limit}")
    public String limit;

    @Value("${offset}")
    public String offset;

    @Value("${type}")
    public String type;

    @Value("${position}")
    public String position;

    SpotifyUserDAO spotifyUserDAO;

    @Autowired
    public SpotifyService(SpotifyUserDAO spotifyUserDAO){
        this.spotifyUserDAO = spotifyUserDAO;
    }

    public String createPlaylist(String accessToken, String userId){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> pathVariables= new HashMap<>();
        pathVariables.put("user_id", userId);

        Map<String, String> body= new HashMap<>();
        body.put("name", playlistName);
        body.put("description", "Songs suggested by OOMFs");
        body.put("public", "false");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, String>> requestHttp = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                createPlaylistURI,
                HttpMethod.POST,
                requestHttp,
                Map.class,
                pathVariables);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            Map<String, Object> responseBody = responseEntity.getBody();
            return (String) responseBody.get("id");
        }

        return "Error";
    }

    public String getTrackEmbed(String url){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> pathVariables= new HashMap<>();
        pathVariables.put("url", url);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                oembedURI,
                HttpMethod.GET,
                null,
                Map.class,
                pathVariables);

        return (String) responseEntity.getBody().get("html");
    }

    // TODO: Make this function async
    public SearchItemDTO getItemsFromSpotify(String accessToken, String query){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        Map<String, String> body= new HashMap<>();

        body.put("q", query);
        body.put("type", type);
        body.put("limit", limit);
        body.put("offset", offset);
        body.put("include_external", "audio");

        HttpEntity<MultiValueMap<String, String>> requestHttp =new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                searchItemURL,
                HttpMethod.GET,
                requestHttp,
                SearchItemDTO.class,
                body)
                .getBody();
    }

    public List<SearchItemResponse> trimSpotifySearchItemResponse(SearchItemDTO searchItemDTO){
        List<SearchItemResponse> searchItemResponses = new ArrayList<>();
        ArrayList<Item> items = searchItemDTO.getTracks().getItems();

        for (Item item: items){
            SearchItemResponse searchItemResponse = new SearchItemResponse();
            searchItemResponse.setImage(item.getAlbum().getImages().get(1).getUrl());

            ArrayList<String> artists = new ArrayList<>();
            for (Artist artist: item.getArtists())
                artists.add(artist.getName());
            searchItemResponse.setArtists(artists);

            searchItemResponse.setName(item.getName());
            searchItemResponse.setUri(item.getUri());
            searchItemResponses.add(searchItemResponse);
        }

        return searchItemResponses;
    }

    public List<SearchItemResponse> searchItems(String username, String query) throws Exception{
        SpotifyDBUser spotifyDBUser = findByUsername(username);

        // Update the access token in case it is expired
        updateAccessToken(spotifyDBUser);

        String accessToken = spotifyDBUser.getAccessToken();

        // Call the Spotify API on the basis of query from user
        SearchItemDTO searchItemDTO = getItemsFromSpotify(accessToken, query);

        // Convert the response from Spotify API into the format that is needed by the frontend
        return trimSpotifySearchItemResponse(searchItemDTO);
    }

    private AccessTokenResponse getAccesTokenFromRefreshToken(String refreshToken, String clientId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "refresh_token");
        requestBody.add("refresh_token", refreshToken);
        requestBody.add("client_id", clientId);
        System.out.println(refreshToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        HttpEntity<AccessTokenResponse> accessTokenResponse = restTemplate.exchange(
                accessTokenURL,
                HttpMethod.POST,
                requestEntity,
                AccessTokenResponse.class);

        System.out.println(accessTokenResponse);
        return accessTokenResponse.getBody();
    }

    public String getSpotifyUserId(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, String>> requestHttp = new HttpEntity<>(headers);

        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange(
                userProfileURI,
                HttpMethod.GET,
                requestHttp,
                UserInfo.class);

        return responseEntity.getBody().getId();
    }

    public AccessTokenResponse getSpotifyAccessToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("grant_type", "authorization_code");
        request.add("code", code);
        request.add("redirect_uri", redirectURI);

        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(
                accessTokenURL,
                HttpMethod.POST,
                requestEntity,
                AccessTokenResponse.class)
                .getBody();
    }

    public String generateRedirectLinkAfterLogin(String baseUrl, String userId){
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path("/" + userId)
                .build()
                .toUriString();
    }

    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();

        String accessToken = registerUserRequest.getAccess_token();
        String refreshToken = registerUserRequest.getRefresh_token();
        Integer expiresIn = registerUserRequest.getExpires_in();
        String clientId = registerUserRequest.getClient_id();

        String userId = getSpotifyUserId(accessToken);
        Optional<SpotifyDBUser> userOptional = getUserDetails(userId);
        registerUserResponse.setUserId(userId);

        // User is not registered
        if (userOptional.isEmpty()) {
            String playlistId = createPlaylist(accessToken, userId);
            saveUser(userId, accessToken, refreshToken, playlistId, expiresIn, clientId);
            registerUserResponse.setIsUserRegistered(false);
        }
        else{
            // User Exists but username is given/not given
            registerUserResponse.setIsUserRegistered((userOptional.get().getUsername() != null));
            updateAccessAndRefreshTokenDetails(userId, refreshToken, accessToken, Instant.now().getEpochSecond(), expiresIn);
        }

        return registerUserResponse;
    }

    // TODO: Make this async
    public void addSongToPlaylist(String username, String songURI) throws Exception{
        SpotifyDBUser spotifyDBUser = findByUsername(username);

        // Update the access token in case it is expired
        updateAccessToken(spotifyDBUser);

        String accessToken = spotifyDBUser.getAccessToken();
        String playlistId = spotifyDBUser.getPlaylistId();

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> pathVariables= new HashMap<>();
        pathVariables.put("playlist_id", playlistId);
        pathVariables.put("position", position);

        Map<String, Object> body= new HashMap<>();
        body.put("uris", Collections.singletonList(songURI));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestHttp = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                addItemsToPlaylistURI,
                HttpMethod.POST,
                requestHttp,
                Map.class,
                pathVariables);
    }

    public void saveUser(String userId, String accessToken, String refreshToken, String playlistId, Integer expiresIn, String clientId){
        SpotifyDBUser spotifyDBUser = new SpotifyDBUser(userId, refreshToken, accessToken, playlistId, expiresIn, clientId);
        spotifyUserDAO.saveAndFlush(spotifyDBUser);
    }

    public void savePlaylistId(String userId, String playlistId){
        spotifyUserDAO.setPlaylistId(userId, playlistId);
    }

    public String getUsername(String userId){
        return spotifyUserDAO.findById(userId).get().getUsername();
    }

    public Optional<SpotifyDBUser> getUserDetails(String userId){
        return spotifyUserDAO.findById(userId);
    }

    public void updateRefreshToken(String userId, String refreshToken){
        spotifyUserDAO.setRefreshToken(userId, refreshToken);
    }

    public boolean userExist(String userId){
        return spotifyUserDAO.existsById(userId);
    }

    public boolean userExistsByUsername(String username){
        return spotifyUserDAO.existsByUsername(username);
    }

    public boolean updateUsername(String username, String userId){
        Optional<SpotifyDBUser> optionalSpotifyDBUser = getUserDetails(userId);
        if (optionalSpotifyDBUser.isPresent()){
            SpotifyDBUser spotifyDBUser = optionalSpotifyDBUser.get();
            spotifyDBUser.setUsername(username);
            spotifyUserDAO.saveAndFlush(spotifyDBUser);
            return true;
        }
        return false;
    }

    public boolean updateAccessAndRefreshTokenDetails(String userId, String refreshToken, String accessToken, Long lastRefreshTime, Integer expiresIn){
        Optional<SpotifyDBUser> optionalSpotifyDBUser = getUserDetails(userId);
        if (optionalSpotifyDBUser.isPresent()){
            SpotifyDBUser spotifyDBUser = optionalSpotifyDBUser.get();
            spotifyDBUser.setRefreshToken(refreshToken);
            spotifyDBUser.setAccessToken(accessToken);
            spotifyDBUser.setLastRefreshTime(lastRefreshTime);
            spotifyDBUser.setExpiresIn(expiresIn);
            spotifyUserDAO.saveAndFlush(spotifyDBUser);
            return true;
        }
        return false;
    }

    public SpotifyDBUser findByUsername(String username){
        return spotifyUserDAO.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public String updateAccessToken(SpotifyDBUser spotifyDBUser){
        Integer expiresIn = spotifyDBUser.getExpiresIn();
        Long lastRefreshTime = spotifyDBUser.getLastRefreshTime();
        Long currentTime = Instant.now().getEpochSecond();
        String refreshToken = spotifyDBUser.getRefreshToken();
        String accessToken = spotifyDBUser.getAccessToken();
        String clientId = spotifyDBUser.getClientId();

        // Access Token has expired, we need to call it again
        if (currentTime - lastRefreshTime > expiresIn){
            AccessTokenResponse accessTokenResponse = getAccesTokenFromRefreshToken(refreshToken, clientId);
            spotifyDBUser.setAccessToken(accessTokenResponse.getAccessToken());
            spotifyDBUser.setExpiresIn(accessTokenResponse.getExpiresIn());
            spotifyDBUser.setLastRefreshTime(Instant.now().getEpochSecond());

            // Update the access token
            accessToken = accessTokenResponse.getAccessToken();

            // Save the updated access token in DB
            spotifyUserDAO.saveAndFlush(spotifyDBUser);
        }

        return accessToken;
    }
}



package com.spotify.app.controller;

import com.spotify.app.models.SongPackage.SongRequest;
import com.spotify.app.models.searchitems.response.SearchItemResponse;
import com.spotify.app.models.user.request.RegisterUserRequest;
import com.spotify.app.models.user.response.RegisterUserResponse;
import com.spotify.app.models.username.response.UsernameResponse;
import com.spotify.app.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class Controller {
    public SpotifyService  spotifyService;

    @Autowired
    public Controller(SpotifyService spotifyService){
        this.spotifyService = spotifyService;
    }

    @GetMapping("/test")
    public String test(){
        return "This is working!";
    }

    @GetMapping("/songs/{username}/{query}")
    public ResponseEntity<List<SearchItemResponse>> getTracks(@PathVariable String username, @PathVariable String query) throws Exception {
        return new ResponseEntity<>(spotifyService.searchItems(username, query), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws IOException{
        RegisterUserResponse registerUserResponse =  spotifyService.registerUser(registerUserRequest);
        return new ResponseEntity<>(registerUserResponse, HttpStatus.CREATED);
    }

    @PostMapping("/user/{username}/tracks")
    public ResponseEntity<Map<String, String>> addSongToPlaylist(@PathVariable String username, @RequestBody SongRequest songRequest) throws Exception{
        spotifyService.addSongToPlaylist(username, songRequest.getSong_uri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Map<String, String>> checkUsername(@PathVariable String username){
        Map<String, String> responseBody = new HashMap<>();
        HttpStatus responseCode;

        if (spotifyService.userExistsByUsername(username)){
            responseBody.put("message", "Username already exists!");
            responseCode = HttpStatus.CONFLICT;
        }
        else{
            responseBody.put("message", "Username does not exist!");
            responseCode = HttpStatus.OK;
        }

        return new ResponseEntity<>(responseBody, responseCode);
    }

    @PatchMapping("/user/{userId}")
    public ResponseEntity<Map<String, String>> addUsername(@RequestBody Map<String, String> requestBody, @PathVariable String userId){
        Map<String, String> responseBody = new HashMap<>();
        HttpStatus responseCode;

        if (spotifyService.updateUsername(requestBody.getOrDefault("username", null), userId)) {
            responseBody.put("message", "Username updated!");
            responseCode = HttpStatus.OK;
        }
        else{
            responseBody.put("message", "User Not Found!");
            responseCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, responseCode);
    }

    @GetMapping("/user/username/{userId}")
    public ResponseEntity<UsernameResponse> getUsername(@PathVariable String userId){
        return new ResponseEntity<>(new UsernameResponse(spotifyService.getUsername(userId)), HttpStatus.OK);
    }
}

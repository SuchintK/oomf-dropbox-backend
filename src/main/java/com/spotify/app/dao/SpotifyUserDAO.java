package com.spotify.app.dao;

import com.spotify.app.models.UserDB.SpotifyDBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpotifyUserDAO extends JpaRepository<SpotifyDBUser, String> {
    @Query("UPDATE SpotifyDBUser user set user.playlistId = :playlistId WHERE user.userId = :userId")
    public void setPlaylistId(@Param("userId") String userId, @Param("playlistId") String playlistId);
    @Modifying
    @Query("UPDATE SpotifyDBUser user set user.refreshToken = :refreshToken WHERE user.userId = :userId")
    public void setRefreshToken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);

    public boolean existsByUsername(String username);

    public Optional<SpotifyDBUser> findByUsername(String username);
}

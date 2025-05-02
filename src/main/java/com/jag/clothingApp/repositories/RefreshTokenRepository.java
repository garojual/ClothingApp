package com.jag.clothingApp.repositories;

import com.jag.clothingApp.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    void deleteByUserEmail(String userEmail); //Delete tokens by userEmail

}

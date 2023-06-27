package practice.toyproject.token.service;

import org.springframework.stereotype.Service;
import practice.toyproject.token.entity.Token;

public interface TokenService {
    Token saveToken(String userId);
    Token selectTokenByUserId(String userId);
    Token updateAccessToken(String userId);
    Token updateRefreshToken(String userId);
}

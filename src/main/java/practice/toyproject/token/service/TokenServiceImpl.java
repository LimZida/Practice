package practice.toyproject.token.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.repository.TokenRepository;
import practice.toyproject.token.util.JwtService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, JwtService jwtService) {
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
    }


    @Override
    public Token saveToken(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 1);
        //refreshJWT (30일)
        String refreshJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 30);

        Token token=Token.builder()
                .userId(userId)
                .accessJwt(accessJWT)
                .refreshJwt(refreshJWT)
                .build();
        return tokenRepository.save(token);
    }

    @Override
    public Token selectTokenByUserId(String userId) {
        return tokenRepository.findTokenByUserId(userId);
    }

    @Override
    public Token updateAccessToken(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 1);

        return tokenRepository.updateAccessJwtByUserIdAndAccessJwt(userId,accessJWT);
    }

    @Override
    public Token updateRefreshToken(String userId) {
        //refreshJWT (30일)
        String refreshJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 30);

        return tokenRepository.updateRefreshJwtByUserIdAndRefreshJwt(userId,refreshJWT);
    }
}

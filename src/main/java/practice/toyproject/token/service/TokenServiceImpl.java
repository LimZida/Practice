package practice.toyproject.token.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger= LoggerFactory.getLogger(TokenServiceImpl.class);

    @Override
    public Token saveToken(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 1);
        //refreshJWT (30일)
        String refreshJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 30);

        logger.info("####### userId 정보 : {}",userId);
        logger.info("####### accessJWT 정보 : {}",accessJWT);
        logger.info("####### refreshJWT 정보 : {}",refreshJWT);

        Token token=Token.builder()
                .userId(userId)
                .accessJwt(accessJWT)
                .refreshJwt(refreshJWT)
                .build();

        return tokenRepository.save(token);
    }

    @Override
    public Token selectTokenByUserId(String userId) {
        logger.info("####### userId 정보 : {}",userId);
        return tokenRepository.findTokenByUserId(userId);
    }

    @Override
    public Token updateAccessToken(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 1);
        logger.info("####### userId 정보 : {}",userId);
        logger.info("####### accessJWT 정보 : {}",accessJWT);

        return tokenRepository.updateAccessJwtByUserIdAndAccessJwt(userId,accessJWT);
    }

    @Override
    public Token updateRefreshToken(String userId) {
        //refreshJWT (30일)
        String refreshJWT = jwtService.createToken(userId, 1000L * 60 * 60 * 24 * 30);
        logger.info("####### userId 정보 : {}",userId);
        logger.info("####### refreshJWT 정보 : {}",refreshJWT);

        return tokenRepository.updateRefreshJwtByUserIdAndRefreshJwt(userId,refreshJWT);
    }
}

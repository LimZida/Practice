package practice.toyproject.token.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.repository.TokenRepository;
import practice.toyproject.token.util.JwtUtil;
/**
 * title : TokenServiceImpl
 *
 * description : saveTokenService(String userId) => 토큰정보(유저, 토큰 2개) 저장
 *               selectTokenService(String userId) => ID를 통한 토큰 조회
 *               checkTokenExpiredService(String userId) => ID를 통한 토큰 조회/만료 확인 후 업데이트
 *               updateAccessTokenService(String userId) => 만료시 AccessJWT 업데이트
 *               updateRefreshTokenService(String userId) => 만료시 RefreshJWT 업데이트
 *               
 * reference : Optional https://mangkyu.tistory.com/70
 *             메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 좋다.
 *             즉, Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.
 *
 *
 *
 * author : 임현영
 * date : 2023.05.31
 **/
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, JwtUtil jwtUtil) {
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
    }

    private final Logger logger= LoggerFactory.getLogger(TokenServiceImpl.class);

    @Override
    public Token saveTokenService(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtUtil.createToken(userId+"Access", 1000L * 60 * 60 * 24 * 1);
        //refreshJWT (30일)
        String refreshJWT = jwtUtil.createToken(userId+"Refresh", 1000L * 60 * 60 * 24 * 30);

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
    public Boolean checkTokenExpiredService(String userId) {
        Token resultToken = selectTokenService(userId);

        String accessJwt = resultToken.getAccessJwt();
        boolean checkAccessJWT = jwtUtil.validateToken(accessJwt);

        String refreshJwt = resultToken.getRefreshJwt();
        boolean checkRefreshJWT = jwtUtil.validateToken(refreshJwt);

        logger.info("####### checkAccessJWT 정보 : {}",checkAccessJWT);
        logger.info("####### checkRefreshJWT 정보 : {}",checkRefreshJWT);

        if(!checkAccessJWT && checkRefreshJWT){
            updateAccessJWTService(userId);
        }
        else if(!checkAccessJWT && !checkRefreshJWT){
            updateAccessJWTService(userId);
            updateRefreshJWTService(userId);
        }

        return true;
    }

    @Override
    public Token selectTokenService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenRepository.findTokenByUserId(userId);
    }

    @Override
    public void updateAccessJWTService(String userId) {
        //accessJWT (1일)
        String accessJWT = jwtUtil.createToken(userId+"Access", 1000L * 60 * 60 * 24 * 1);
        logger.info("####### userId 정보 : {}",userId);
        logger.info("####### accessJWT 정보 : {}",accessJWT);

        tokenRepository.updateAccessJwtByUserIdAndAccessJwt(userId,accessJWT);
    }

    @Override
    public void updateRefreshJWTService(String userId) {
        //refreshJWT (30일)
        String refreshJWT = jwtUtil.createToken(userId+"Refresh", 1000L * 60 * 60 * 24 * 30);
        logger.info("####### userId 정보 : {}",userId);
        logger.info("####### refreshJWT 정보 : {}",refreshJWT);

        tokenRepository.updateRefreshJwtByUserIdAndRefreshJwt(userId,refreshJWT);
    }
}

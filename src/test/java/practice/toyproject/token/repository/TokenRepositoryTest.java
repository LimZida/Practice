package practice.toyproject.token.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import practice.toyproject.token.entity.Token;
import practice.toyproject.util.JWT.JwtProvider;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title : TokenRepositoryTest
 * description : Token entity 사용한 tokenRepository test
 *
 * reference : TestPropertySource으로 Junit에서 프로퍼티 사용하기: https://jaehoney.tistory.com/218
 *
 *             참고: @DataJpaTest 어노테이션은 JPA 관련 컴포넌트를 테스트하기 위한 슬라이스 테스트입니다.
 *             따라서 JPA 관련 컴포넌트에 대한 빈만 자동으로 등록됩니다. JwtService는 JPA 관련 컴포넌트가 아니므로 자동으로 등록되지 않습니다.
 *
 * author : 임현영
 * date : 2023.06.27
 **/

@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.properties")
@Transactional
class TokenRepositoryTest {
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    TokenRepositoryTest(TokenRepository tokenRepository, JwtProvider jwtProvider) {
        this.tokenRepository = tokenRepository;
        this.jwtProvider = jwtProvider;
    }

    private final Logger logger = LoggerFactory.getLogger(TokenRepositoryTest.class);

    @Test
    void save() {
        String userId="zida4472";
//        String accessJWT = jwtUtil.createToken(userId, 1000L * 60 * 60 * 24 * 1);
//        String refreshJWT = jwtUtil.createToken(userId, 1000L * 60 * 60 * 24 * 30);

        Token token=Token.builder()
                .userId(userId)
//                .accessJwt(accessJWT)
//                .refreshJwt(refreshJWT)
                .build();
        tokenRepository.save(token);
        logger.info("###### save token값 {} :",token);
//
//        Token result = tokenRepository.findTokenByUserId(userId);
//        assertThat(result.getAccessJwt()).isEqualTo(token.getAccessJwt());
//        assertThat(result.getRefreshJwt()).isEqualTo(token.getRefreshJwt());
    }

    @Test
    void findTokenByUserId() {
        String userId="zida4470";
//        Token result = tokenRepository.findTokenByUserId(userId);
//
//        assertThat(result.getUserId()).isEqualTo(userId);
    }

}
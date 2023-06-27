package practice.toyproject.token.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * title : JwtServiceTest
 * description : User entity 사용한 userRepository test
 *
 * reference : TestPropertySource으로 Junit에서 프로퍼티 사용하기: https://jaehoney.tistory.com/218
               테스트를 위한 다양한 어노테이션들: https://mangkyu.tistory.com/242
 *             Runwith (Junit 4) 와 ExtendWith (Junit 5): https://findmypiece.tistory.com/173
 * author : 임현영
 * date : 2023.06.27
 **/
@SpringBootTest(classes = JwtService.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
class JwtServiceTest {
    private final JwtService jwtService;

    @Autowired
    JwtServiceTest(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Test
    public void createToken() {
        Long date=1000L * 60 * 60 * 24 * 30;
        String token = jwtService.createToken("zida4470@naver.com", date);
        System.out.println(token);
    }

    @Test
    void validateToken() {
        Long date=1000L * 60 * 60 * 24 * 30;
        String token = jwtService.createToken("zida4470@naver.com", date);
        boolean result = jwtService.validateToken(token);
        System.out.println(result);
    }
//
//    @Test
//    void getInformation() {
//    }
}
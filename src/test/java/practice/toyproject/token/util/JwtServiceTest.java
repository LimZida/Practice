package practice.toyproject.token.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Base64;

@DataJpaTest
class JwtServiceTest {
    private final JwtService jwtService;
    @Autowired
    JwtServiceTest() {
        String secretKey = Base64.getEncoder().encodeToString("111111111111111111111111111111111111111111".getBytes());
        this.jwtService = new JwtService(secretKey);
    }

    @Test
    public void createToken() {
        Long date=1000L * 60 * 60 * 24 * 30;
        String token = jwtService.createToken("zida4470@naver.com", date);
        System.out.println(token);
    }

//    @Test
//    void validateToken() {
//    }
//
//    @Test
//    void getInformation() {
//    }
}
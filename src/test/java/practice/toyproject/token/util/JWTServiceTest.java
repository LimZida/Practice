package practice.toyproject.token.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JWTServiceTest {
    private final JWTService jwtService;

    @Autowired
    JWTServiceTest(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Test
    void createToken() {
    }

    @Test
    void validateToken() {
    }

    @Test
    void getInformation() {
    }
}
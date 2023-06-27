package practice.toyproject.token.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

/**
 * title : JWTService
 *
 * description : createToken(userId) => 해당 아이디에 대한 토큰을 만드는 함수
 * *			 validateToken(jwtToken) => 토큰의 유효기간을 검사하는 함수
 * *			 getInformation(token) => 암호화된 토큰의 정보를 해독하는 함수
 *
 * reference : 환경변수 가져오는 법 https://kim-jong-hyun.tistory.com/17
 *             Test 환경에서 Value 못쓰는 이유: https://chat.openai.com/share/7515cac0-7d3f-4523-8c7f-42beef6ca768
 *             JwtService 클래스의 secretKey 필드를 초기화하는 부분에서 문제가 발생하는 것 같습니다. @Value 어노테이션을 사용하여 encryptKey 프로퍼티 값을 주입하려고 하지만, 테스트 환경에서는 @Value 어노테이션을 사용할 수 없습니다.
 *             테스트 클래스에서 @DataJpaTest 어노테이션을 사용하고 있는데, 이 어노테이션은 JPA 관련 컴포넌트만을 테스트하기 위한 것입니다. 따라서 JwtService 클래스의 @Value 어노테이션을 사용하는 부분은 테스트 환경에서 작동하지 않습니다.
 *             해결 방법으로는 다음과 같이 JwtService 클래스의 생성자를 통해 secretKey 값을 주입받도록 변경하는 것입니다:
 *
 *
 * author : 임현영
 * date : 2023.06.27
 **/
@Component
public class JwtService {
    //암복호화에 사용되는 키 값
    //해당 방식으로 초기화 진행
    private final String secretKey;
    public JwtService(@Value("${jwt.key}") String encryptKey) {
        this.secretKey = Base64.getEncoder().encodeToString(encryptKey.getBytes());
    }

    public String createToken(String userId,Long expiredDate) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims) // 데이터를 넣어 줍니다
                .setIssuedAt(now)   // 토큰 발행 일자
//                .setExpiration(new Date(now.getTime() + (1000L * 60 * 60 * 24 * 30))) // ex) 유효 기간 30일
                .setExpiration(new Date(now.getTime() + expiredDate))
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘과 암복호화에 사용할 키
                .compact(); // Token 생성
    }

    // Jwt Token의 유효성 및 만료 기간 검사
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Jwt Token에서 데이터를 전달
    public Claims getInformation(String token) {
        Claims claims =Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims;
    }
}

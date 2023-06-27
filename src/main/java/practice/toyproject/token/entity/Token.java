package practice.toyproject.token.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * title : entity
 *
 * description : T_TOKEN 테이블 컬럼 매핑용 entity
 *
 * reference : 생성자 https://kadosholy.tistory.com/91
 * 롬복  https://www.daleseo.com/lombok-popular-annotations/
 * 엔티티 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 * 엔티티 setter 쓰지않는 이유 https://velog.io/@langoustine/setter%EB%A5%BC-%EC%93%B0%EC%A7%80%EB%A7%90%EB%9D%BC%EA%B3%A0
 *                          https://velog.io/@aidenshin/%EB%82%B4%EA%B0%80-%EC%83%9D%EA%B0%81%ED%95%98%EB%8A%94-JPA-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%9E%91%EC%84%B1-%EC%9B%90%EC%B9%99
 * 빌더 https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *
 * author : 임현영
 * date : 2023.06.27
 **/
@Getter
@ToString // 로그 debug 시 toString 자동생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new 객체 생성을 막고, Builder와 호환
@Entity(name = "T_TOKEN")
public class Token {
    //유저 아이디
    @Id
    private String userId;
    //엑세스 토큰
    private String accessJwt;
    //리프레시 토큰
    private String refreshJwt;

    @Builder
    public Token(String userId, String accessJwt, String refreshJwt){
        this.userId=userId;
        this.accessJwt=accessJwt;
        this.refreshJwt=refreshJwt;
    }
}

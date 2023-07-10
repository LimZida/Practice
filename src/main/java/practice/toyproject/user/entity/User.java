package practice.toyproject.user.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * title : entity
 *
 * description : T_USER 테이블 컬럼 매핑용 entity
 *
 * reference : 생성자 https://kadosholy.tistory.com/91
 * 롬복  https://www.daleseo.com/lombok-popular-annotations/
 * 롬복을 쓸 경우 주의할 점 https://www.nowwatersblog.com/springboot/springstudy/lombok
 *                      https://velog.io/@rosa/Lombok-%EC%A7%80%EC%96%91%ED%95%B4%EC%95%BC-%ED%95%A0-annotation
 * 엔티티 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 * 엔티티 setter 쓰지않는 이유 https://velog.io/@langoustine/setter%EB%A5%BC-%EC%93%B0%EC%A7%80%EB%A7%90%EB%9D%BC%EA%B3%A0
 *                         https://velog.io/@aidenshin/%EB%82%B4%EA%B0%80-%EC%83%9D%EA%B0%81%ED%95%98%EB%8A%94-JPA-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%9E%91%EC%84%B1-%EC%9B%90%EC%B9%99
 * Timestamp https://kyhslam.tistory.com/entry/1-Springboot-JPA-Oracle-%EC%97%B0%EB%8F%99-%EC%84%A4%EC%A0%95
 *           https://velog.io/@koo8624/Spring-CreationTimestamp-UpdateTimestamp
 * 빌더 : https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *       https://pamyferret.tistory.com/67
 *
 * author : 임현영
 * date : 2023.05.24
 **/
//@Data => getter,setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 자동생성
//@AllArgsConstructor 모든 매개변수를 가진 생성자도 추가
//@NoArgsConstructor 파라미터가 없는 생성자 생성
//@ToString Stringbuffer 역할

@Getter
@ToString // 로그 debug 시 toString 자동생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected 접근 제어자를 가진 기본 생성자를 자동으로 생성해주는 역할(다른 패키지의 기본 생성자 호출을 막음), Builder와 호환
@Entity(name = "T_USER") //DB 테이블과 매핑
public class User{

//    @SequenceGenerator(
//            name = "USER_SEQ_GENERATOR", // 여기서 사용할 시퀀스 이름
//            sequenceName = "SEQ_T_USER", //매핑할 데이터베이스 시퀀스 이름
//            initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
//    // 일련번호
//    private long seq;
    @Id //PK
    // 이메일
    private String userId;
    // 로그인 횟수
    private long loginCnt;
    // 로그인 실패횟수
    private long loginFailCnt;
    // 이름
    private String userName;
    // 핸드폰
    private String userHp;
    // 비밀번호
    private String userPw;
    // 프로필 이미지
    private String imageUrl;
    // 개인정보 수정날짜
    private Timestamp updateDate;
    // 가입일자
    @CreationTimestamp
    private Timestamp regDate;
    // 마지막 로그인
    // @UpdateTimestamp
    private Timestamp lastLoginDate;
    // 비밀번호 수정날짜
    @CreationTimestamp
    private Timestamp updPwDate;

    //빌더패턴 사용
    @Builder
    private User(String userId,String userPw,String userName,String imageUrl,
                Timestamp updateDate,Timestamp regDate,Timestamp updPwDate,Timestamp lastLoginDate, long loginCnt, long loginFailCnt, String userHp){
        this.userId=userId;
        this.userPw=userPw;
        this.userName=userName;
        this.imageUrl=imageUrl;
        this.updateDate=updateDate;
        this.regDate=regDate;
        this.lastLoginDate=lastLoginDate;
        this.updPwDate=updPwDate;
        this.loginCnt=loginCnt;
        this.loginFailCnt=loginFailCnt;
        this.userHp=userHp;
    }
}

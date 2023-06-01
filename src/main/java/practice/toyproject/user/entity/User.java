package practice.toyproject.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * title : entity
 *
 * description : T_USER 테이블 컬럼 매핑용 entity
 *
 * reference : 생성자 https://kadosholy.tistory.com/91
 * 롬복  https://www.daleseo.com/lombok-popular-annotations/
 * 엔티티 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 * Timestamp https://kyhslam.tistory.com/entry/1-Springboot-JPA-Oracle-%EC%97%B0%EB%8F%99-%EC%84%A4%EC%A0%95
 *
 * author : 임현영
 * date : 2023.05.24
 **/
@Data
//getter,setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 자동생성
//@AllArgsConstructor 모든 매개변수를 가진 생성자도 추가
//@NoArgsConstructor 파라미터가 없는 생성자 생성
//@ToString Stringbuffer 역할
@Entity(name = "T_USER") //DB 테이블과 매핑
public class User {

    @Id //PK
    @SequenceGenerator(
            name = "MEMBER_SEQ_GENERATOR", // 여기서 사용할 시퀀스 이름
            sequenceName = "SEQ_T_USER", //매핑할 데이터베이스 시퀀스 이름
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private long seq;
    private String userId;
    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp lastLoginDate;
}

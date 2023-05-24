package practice.toyproject.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * title : entity
 * description : T_USER 테이블 컬럼 매핑용 entity
 * reference : 생성자 관련 https://kadosholy.tistory.com/91
 * 롬복 어노테이션 https://www.daleseo.com/lombok-popular-annotations/
 * 엔티티 어노테이션 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 * author : 임현영
 * date : 2023.05.24
 **/
@Data //getter,setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 자동생성
//@AllArgsConstructor // 모든 매개변수를 가진 생성자도 추가
//@NoArgsConstructor  // 파라미터가 없는 생성자 생성
//@ToString // Stringbuffer 역할
@Entity(name = "T_USER") //DB 테이블과 매핑
public class Member {

    @Id
    private int seq;

    private String memberId;

}

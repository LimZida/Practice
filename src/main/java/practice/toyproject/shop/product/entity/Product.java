package practice.toyproject.shop.product.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * title : Product
 *
 * description : T_PRODUCT 테이블 컬럼 매핑용 entity
 *
 * reference :  생성자 https://kadosholy.tistory.com/91
 *              롬복  https://www.daleseo.com/lombok-popular-annotations/
 *              롬복을 쓸 경우 주의할 점 https://www.nowwatersblog.com/springboot/springstudy/lombok
 *                                   https://velog.io/@rosa/Lombok-%EC%A7%80%EC%96%91%ED%95%B4%EC%95%BC-%ED%95%A0-annotation
 *              엔티티 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 *              엔티티 setter 쓰지않는 이유 https://velog.io/@langoustine/setter%EB%A5%BC-%EC%93%B0%EC%A7%80%EB%A7%90%EB%9D%BC%EA%B3%A0
 *                                       https://velog.io/@aidenshin/%EB%82%B4%EA%B0%80-%EC%83%9D%EA%B0%81%ED%95%98%EB%8A%94-JPA-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%9E%91%EC%84%B1-%EC%9B%90%EC%B9%99
 *              Timestamp https://kyhslam.tistory.com/entry/1-Springboot-JPA-Oracle-%EC%97%B0%EB%8F%99-%EC%84%A4%EC%A0%95
 *                        https://velog.io/@koo8624/Spring-CreationTimestamp-UpdateTimestamp
 *              빌더 : https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *              https://pamyferret.tistory.com/67
 *
 * author : 임현영
 * date : 2023.07.14
 **/
@Getter
@ToString // 로그 debug 시 toString 자동생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected 접근 제어자를 가진 기본 생성자를 자동으로 생성해주는 역할(다른 패키지에서 기본 생성자 호출을 막음), Builder와 호환
@Entity(name = "T_PRODUCT")
public class Product {
    @SequenceGenerator(
            name = "PRODUCT_SEQ_GENERATOR", // 여기서 사용할 시퀀스 이름
            sequenceName = "SEQ_T_PRODUCT", //매핑할 데이터베이스 시퀀스 이름
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
    @Id
    private Long productSeq;
    private String productImgUrl;
    private String productType; //코드화 분류 필요
    private String productName;
    @CreationTimestamp
    private Timestamp productRegDate;
    private String productUseYn;
    private String productPrice;
    private String productDescription;

    @Builder
    private Product(Long productSeq, String productImgUrl, String productType, String productName, Timestamp productRegDate, String productUseYn
                    ,String productPrice, String productDescription){
        this.productSeq=productSeq;
        this.productImgUrl=productImgUrl;
        this.productType=productType;
        this.productName=productName;
        this.productRegDate=productRegDate;
        this.productUseYn=productUseYn;
        this.productPrice=productPrice;
        this.productDescription=productDescription;
    }

}

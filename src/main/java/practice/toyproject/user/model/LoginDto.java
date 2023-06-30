package practice.toyproject.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * title : LoginDto
 *
 * description : 로그인 json request 매핑용 dto
 *
 * reference : cannot deserialize from object value 해결법 : https://charactermail.tistory.com/488, https://azurealstn.tistory.com/74
 *             request 받았는데 dto 매핑이 안되는 경우 : https://velog.io/@ssol_916/RequestBody%EB%A1%9C-%EB%B0%9B%EC%95%98%EB%8A%94%EB%8D%B0-null%EC%9D%B8-%EA%B2%BD%EC%9A%B0
 *
 * author : 임현영
 * date : 2023.06.30
 **/
@Getter
@ToString
@NoArgsConstructor //파라미터가 없는 생성자 생성
public class LoginDto {
    private String userId;
    private String userPw;
    private String accessJWT;
    private String refreshJWT;
    private String tokenType;

    @Builder
    public LoginDto(String userId, String userPw, String accessJWT, String refreshJWT, String tokenType){
        this.userId=userId;
        this.userPw = userPw;
        this.accessJWT=accessJWT;
        this.refreshJWT=refreshJWT;
        this.tokenType=tokenType;
    }
}

package practice.toyproject.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
//request body 사용하려면 logindto 빈껍데기만들어야함
@Getter
@ToString
public class LoginDto {
    private String userId;
    private String userPw;
    private String accessJWT;
    private String refreshJWT;
    private String tokenType;

    public LoginDto(){

    }

    @Builder
    public LoginDto(String userId, String userPw, String accessJWT, String refreshJWT, String tokenType){
        this.userId=userId;
        this.userPw = userPw;
        this.accessJWT=accessJWT;
        this.refreshJWT=refreshJWT;
        this.tokenType=tokenType;
    }
}

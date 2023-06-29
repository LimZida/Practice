package practice.toyproject.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginDto {
    private String userId;
    private String userPw;
    private String accessJWT;
    private String tokenType;

    public LoginDto(){

    }

    @Builder
    public LoginDto(String userId, String userPw, String accessJWT, String tokenType){
        this.userId=userId;
        this.userPw = userPw;
        this.accessJWT=accessJWT;
        this.tokenType=tokenType;
    }
}

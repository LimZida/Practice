package practice.toyproject.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpDto {
    // 아이디
    private String userId;
    // 핸드폰
    private String userHp;
    // 비밀번호
    private String userPw;
    // 이름
    private String userName;

    public SignUpDto(){

    }

    @Builder
    public SignUpDto(String userId, String userPw,String userHp,String userName){
        this.userId=userId;
        this.userPw=userPw;
        this.userHp=userHp;
        this.userName=userName;
    }
}

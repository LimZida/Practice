package practice.toyproject.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.service.TokenService;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.service.UserService;

import java.util.List;

/**
 * title : userController
 * description : saveService(userId) => 유저 저장
 *               selectAllService() => 모든 유저 조회
 *               selectSeqService(seq) => 시퀀스로 유저 조회
 *               selectIdService(userId) => ID로 유저 조회
 *
 * reference : RESTful 설계 규칙 https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *
 * author : 임현영
 * date : 2023.05.24
 **/
@RestController
@RequestMapping("/shop")
public class UserController {
    //생성자 주입 (Autowired 생략가능)
    private final UserService userService;
    private final TokenService tokenService;
    @Autowired
    public UserController(UserService userService, TokenService tokenService){
        this.userService=userService;
        this.tokenService = tokenService;
    }
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    // 로그인(유저 조회)
    @RequestMapping(value =("/login") ,method =RequestMethod.POST)
    public String selectIdService(String userId,String userPw){
        logger.info("####### 유저 조회용 아이디 파라미터 : {}",userId+" "+userPw);

        Boolean loginResult = userService.selectUserByUserIdAndUserPw(userId, userPw);

        if(loginResult){
            Token token = tokenService.saveToken(userId);
            return token.getAccessJwt();
        }
        else{
            return null;
        }
    }
    
    // 회원가입(유저 저장)
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User saveService(String userId,String userHp, String userPw,Long loginCnt,Long loginFailCnt){
        logger.info("####### 유저 저장용 파라미터 : {}",userId);
        return userService.saveUser(userId,userHp,userPw,loginCnt,loginFailCnt);
    }

    // 유저 모두 조회
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> selectAllService(){
        logger.info("####### 모든 유저 조회");
        return userService.selectAllUser();
    }

    // 유저 일련번호로 조회
//    @RequestMapping(value ="/seq" ,method = RequestMethod.GET)
//    public User selectSeqService(@RequestParam("seq") long seq){
//        logger.info("####### 유저 조회용 시퀀스 파라미터 : {}",seq);
//        return userService.selectUserBySeq(seq);
//    }
}

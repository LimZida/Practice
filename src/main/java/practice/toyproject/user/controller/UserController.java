package practice.toyproject.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.service.TokenService;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.model.LoginDto;
import practice.toyproject.user.model.SignUpDto;
import practice.toyproject.user.service.UserService;

import java.util.List;

/**
 * title : userController
 * description : saveServiceContoller(userId) => 유저 저장
 *               selectAllServiceContoller() => 모든 유저 조회
 *               selectSeqServiceContoller(seq) => 시퀀스로 유저 조회
 *               selectIdServiceContoller(userId) => ID로 유저 조회
 *
 * reference : RESTful 설계 규칙 https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *             @RequestBody란?
 *             jackson library object mapper
 *             JSON Serialize 알아보기
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
    public LoginDto selectUserContoller(@RequestBody LoginDto loginDto){
        logger.info("####### 유저 조회용  파라미터 : {}",loginDto.toString());

//        //로그인 시도
//        Boolean loginResult = userService.selectUserService(loginDto);
//
//        //로그인 성공 시
//        if(loginResult){
//            //토큰 발급 후 헤더에 담아 응답
//            Token token = tokenService.saveTokenService(userId);
//            return token.getAccessJwt();
//        }
//        else{
//            return null;
//        }
        return userService.selectUserService(loginDto);
    }
    
    // 회원가입(유저 저장)
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public User saveUserContoller(@RequestBody SignUpDto signUpDto){
        logger.info("####### 유저 저장용 파라미터 : {}",signUpDto.toString());
        return userService.saveUserService(signUpDto);
    }

    // 유저 모두 조회
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> selectAllUserController(){
        logger.info("####### 모든 유저 조회");
        return userService.selectAllUserService();
    }

    // 유저 일련번호로 조회
//    @RequestMapping(value ="/seq" ,method = RequestMethod.GET)
//    public User selectSeqService(@RequestParam("seq") long seq){
//        logger.info("####### 유저 조회용 시퀀스 파라미터 : {}",seq);
//        return userService.selectUserBySeq(seq);
//    }
}

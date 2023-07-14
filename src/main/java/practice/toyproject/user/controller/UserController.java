package practice.toyproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practice.toyproject.user.dto.UserDto;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.service.UserService;

import java.util.List;

/**
 * title : userController
 * description : loginContoller(@RequestBody LoginDto loginDto) => 로그인
 *               signUpContoller(@RequestBody SignUpDto signUpDto) => 회원가입
 *               selectAllUserController() => 모든 유저 조회
 *
 * reference : RESTful 설계 규칙 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *             @RequestBody란? : https://dev-coco.tistory.com/95 , https://cheershennah.tistory.com/179
 *
 * author : 임현영
 * date : 2023.05.24
 **/
@RestController
@RequestMapping("/shop")
public class UserController {
    //생성자 주입 (Autowired 생략가능)
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    
    // 로그인(유저 조회)
    @PostMapping("/login")
    public UserDto.loginInfo login(@RequestBody UserDto.loginInfo loginInfo){
        return userService.loginService(loginInfo);
    }
    
    // 회원가입(유저 저장)
    @PostMapping("/signup")
    public UserDto.signupInfo signUp(@RequestBody UserDto.signupInfo signupInfo){
        return userService.signUpService(signupInfo);
    }

    // 유저 모두 조회
    @GetMapping( "/select")
    public List<User> allUser(){
        return userService.viewService();
    }
}

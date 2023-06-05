package practice.toyproject.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.service.UserService;

import java.util.List;

/**
 * title : userController
 * description : sayTest() => 테스트
 *               saveService(userId) => 유저 저장
 *               selectSeqService(seq) => 시퀀스로 유저 조회
 *               selectAllService() => 모든 유저 조회
 *               selectIdService(userId) => ID로 유저 조회
 *               
 * reference : RESTful 설계 규칙 https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *
 * author : 임현영
 * date : 2023.05.24
 **/
@RestController
@RequestMapping("/users")
public class UserController {
    //생성자 주입 (Autowired 생략가능)
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String sayTest(){
        logger.info("####### testcontroller 입장: {}","test");
        return "This is test";
    }

    @RequestMapping(value = "/user-save",method = RequestMethod.GET)
    public User saveService(@RequestParam("userId") String userId){
        logger.info("####### 유저 저장용 파라미터 : {}",userId);
        return userService.saveUser(userId);
    }

    @RequestMapping(value ="/seq-selection" ,method = RequestMethod.GET)
    public User selectSeqService(@RequestParam("seq") long seq){
        logger.info("####### 유저 조회용 시퀀스 파라미터 : {}",seq);
        return userService.selectUserBySeq(seq);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> selectAllService(){
        logger.info("####### 모든 유저 조회");
        return userService.selectAllUser();
    }

    @RequestMapping(value =("/userId-selection") ,method =RequestMethod.GET)
    public User selectIdService(@RequestParam("userId") String userId){
        logger.info("####### 유저 조회용 아이디 파라미터 : {}",userId);
        return userService.selectUserByUserId(userId);
    }
}

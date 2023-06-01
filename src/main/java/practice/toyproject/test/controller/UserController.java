package practice.toyproject.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.toyproject.test.entity.User;
import practice.toyproject.test.service.UserService;

/**
 * title : userController
 * description : user 관련 controller
 * reference :
 *
 * author : 임현영
 * date : 2023.05.24
 **/
@RestController
@RequestMapping("/member")
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

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public User saveService(@RequestParam("userId") String userId){
        logger.info("####### 유저 저장용 파라미터: {}",userId);
        return userService.saveUser(userId);
    }

    @RequestMapping(value ="select" ,method = RequestMethod.GET)
    public User selectService(@RequestParam("seq") long seq){
        logger.info("####### 유저 조회용 파라미터: {}",seq);
        return userService.selectUser(seq);
    }
}

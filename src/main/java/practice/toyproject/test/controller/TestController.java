package practice.toyproject.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String sayTest(){
        logger.info("####### testcontroller 입장: {}","test");
        return "This is test";
    }
}

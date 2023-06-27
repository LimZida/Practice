package practice.toyproject.token.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.service.TokenService;
/**
 * title : TokenController
 *
 * description : saveTokenService(String userId) => 토큰정보 저장
 *               checkTokenService(String userId) => 만료기간 체크
 *               updateAccessJwtService(String userId) => 엑세스 토큰 업데이트
 *               updateRefreshJwtService(String userId) =>
 *              
 *
 * reference : RESTful 설계 규칙 https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html
 *
 * author : 임현영
 * date : 2023.05.24
 **/

@RestController
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private final Logger logger= LoggerFactory.getLogger(TokenController.class);

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Token saveTokenService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenService.saveToken(userId);
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Boolean checkTokenService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenService.checkTokenExpired(userId);
    }

//    @RequestMapping(value = "/accessJWT",method = RequestMethod.POST)
//    public void updateAccessJwtService(String userId){
//        logger.info("####### userId 정보 : {}",userId);
//        tokenService.updateAccessJWT(userId);
//    }
//
//    @RequestMapping(value = "/refreshJWT",method = RequestMethod.POST)
//    public void updateRefreshJwtService(String userId){
//        logger.info("####### userId 정보 : {}",userId);
//        tokenService.updateRefreshJWT(userId);
//    }

}

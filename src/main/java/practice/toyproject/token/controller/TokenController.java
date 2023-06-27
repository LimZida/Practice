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

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Token selectTokenService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenService.selectTokenByUserId(userId);
    }

    @RequestMapping(value = "/AccessJWT",method = RequestMethod.POST)
    public Token updateAccessJwtService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenService.updateAccessToken(userId);
    }

    @RequestMapping(value = "/refreshJWT",method = RequestMethod.POST)
    public Token updateRefreshJwtService(String userId){
        logger.info("####### userId 정보 : {}",userId);
        return tokenService.updateRefreshToken(userId);
    }

}

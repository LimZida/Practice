package practice.toyproject.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practice.toyproject.token.entity.Token;
import practice.toyproject.token.repository.TokenRepository;
import practice.toyproject.util.JWT.JwtProvider;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.model.LoginDto;
import practice.toyproject.user.model.SignUpDto;
import practice.toyproject.user.repository.UserRepository;

import java.util.List;

/**
 * title : userServiceImpl
 *
 * description : saveUserService(userId,...) => 유저 저장
 *               selectUserByUserIdService(userId) => ID를 통한 유저 조회
 *               selectAllUserService() => 모든 유저 조회
 *
 * reference : Optional https://mangkyu.tistory.com/70
 * 메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 좋다.
 * 즉, Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.
 *
 *
 *
 * author : 임현영
 * date : 2023.05.31
 **/
@Service
public class UserServiceImpl implements UserService {
    //생성자 주입 (Autowired 생략가능)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, TokenRepository tokenRepository, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.tokenRepository = tokenRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUserService(SignUpDto signUpDto) {
        User user= User.builder()
                .userId(signUpDto.getUserId())
                .userPw(passwordEncoder.encode(signUpDto.getUserPw()))
                .userHp(signUpDto.getUserHp())
                .userName(signUpDto.getUserName())
                .imageUrl("")
                .loginCnt(0)
                .loginFailCnt(0)
                .build();

        logger.info("####### user 정보 : {}",user.toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> selectAllUserService() {
        return userRepository.findAll();
    }

    @Override
    public LoginDto selectUserService(LoginDto loginDto) {
        // user 검증
        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getUserPw());
        logger.info("####### authenticationToken 정보 : {}",authenticationToken);

        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        logger.info("####### authentication getobject 정보 : {}",authenticationManagerBuilder.getObject());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("####### authentication 정보 : {}",authentication.getPrincipal());

        // token 생성
        String accessJWT = jwtProvider.generateAccessToken(authentication);
        String refreshJWT = jwtProvider.generateRefreshToken(authentication);
        logger.info("####### accessJWT 정보 : {}",accessJWT);
        logger.info("####### user 정보 : {}",loginDto.getUserId());

        Token token= Token.builder()
                .userId(loginDto.getUserId())
                .accessJwt(accessJWT)
                .refreshJwt(refreshJWT)
                .build();

        // refresh token 저장
        tokenRepository.save(token);

        return loginDto.builder()
                .accessJWT(accessJWT)
                .refreshJWT(refreshJWT)
                .tokenType("Bearer ")
                .userId(loginDto.getUserId())
                .build();
    }
}

package practice.toyproject.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import practice.toyproject.token.service.TokenService;
import practice.toyproject.user.entity.User;
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
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUserService(String userId, String userHp, String userPw, Long loginCnt, Long loginFailCnt) {
        User user= User.builder()
                .userId(userId)
                .userPw(userPw)
                .userHp(userHp)
                .loginCnt(loginCnt)
                .loginFailCnt(loginFailCnt)
                .build();

        logger.info("####### user 정보 : {}",user.toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> selectAllUserService() {
        return userRepository.findAll();
    }

    @Override
    public Boolean selectUserService(String userId, String userPw) {
        // user 검증
        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token 생성
        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(authentication);
        User user = (User) authentication.getPrincipal(); // user 정보

        // refresh token 저장
        refreshTokenService.saveOrUpdate(user, refreshToken);

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .tokenType("Bearer ")
                .userId(user.getId())
                .build();

        return true;
    }

//    2023 06 28 수정전 원본
//    @Override
//    public Boolean selectUserService(String userId, String userPw) {
//        User loginResult = userRepository.findUserByUserIdAndUserPw(userId, userPw);
//        if(loginResult.getUserId().isEmpty()){
//            return false;
//        }
//        return true;
//    }

    //    @Override
    //    public User selectUserBySeq(long seq) {
    //        return userRepository.findBySeq(seq);
    //    }
}

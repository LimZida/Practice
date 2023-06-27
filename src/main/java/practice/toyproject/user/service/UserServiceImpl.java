package practice.toyproject.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.repository.UserRepository;

import java.util.List;

/**
 * title : userServiceImpl
 *
 * description : saveUser(userId,...) => 유저 저장
 *               selectUserByUserId(userId) => ID를 통한 유저 조회
 *               selectAllUser() => 모든 유저 조회
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
    public User saveUser(String userId,String userHp, String userPw,Long loginCnt,Long loginFailCnt) {
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

//    @Override
//    public User selectUserBySeq(long seq) {
//        return userRepository.findBySeq(seq);
//    }

    @Override
    public List<User> selectAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User selectUserByUserIdAndUserPw(String userId,String userPw) {
        return userRepository.findUserByUserIdAndUserPw(userId,userPw);
    }
}

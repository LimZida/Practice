package practice.toyproject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import practice.toyproject.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * title : userRepository
 * description : save(user) => 유저 저장
 *               findByUserId(userId) => ID를 통해 유저 조회
 *               findAll() => 모든 유저 조회
 *               
 * reference : 쿼리 직접 사용시 https://sundries-in-myidea.tistory.com/91
 *             JPA 메서드 명령규칙 https://zara49.tistory.com/130
 *                              https://ozofweird.tistory.com/entry/%EC%82%BD%EC%A7%88-%ED%94%BC%ED%95%98%EA%B8%B0-JpaRepository-%EA%B7%9C%EC%B9%99%EC%97%90-%EB%A7%9E%EB%8A%94-%EB%A9%94%EC%84%9C%EB%93%9C
 *
 * author : 임현영
 * date : 2023.05.31
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<UserDetails> findUserByUserId(String userId);
    User findUserByUserIdAndUserPw(String userId, String userPw);
    List<User> findAll();
    //    User findBySeq(long seq);
}

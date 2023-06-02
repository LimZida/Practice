package practice.toyproject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.toyproject.user.entity.User;

import java.util.List;

/**
 * title : userRepository
 * description : save(user) => 유저 저장
 *               findBySeq(seq) => 시퀀스를 통해 유저 조회
 *               findAll() => 모든 유저 조회
 * reference : 쿼리 직접 사용시 https://sundries-in-myidea.tistory.com/91
 * JPA 메서드 명령규칙 https://zara49.tistory.com/130
 *
 * author : 임현영
 * date : 2023.05.31
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findBySeq(long seq);
    User findByUserId(String userId);
    List<User> findAll();
}

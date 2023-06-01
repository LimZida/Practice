package practice.toyproject.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practice.toyproject.test.entity.User;

/**
 * title : userRepository
 * description : User entity 사용한 userRepository
 * reference : 쿼리 직접 사용시 https://sundries-in-myidea.tistory.com/91
 * JPA 메서드 명령규칙 https://zara49.tistory.com/130
 *
 * author : 임현영
 * date : 2023.05.31
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    @Query(value = "SELECT U FROM T_USER U WHERE U.seq = ?1")
    User findBySeq(long seq);
}

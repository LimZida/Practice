package practice.toyproject.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.toyproject.user.entity.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title : UserRepositoryTests
 * description : User entity 사용한 userRepository test
 *
 * reference : 테스트 예제 https://www.wool-dev.com/backend-engineering/spring/spring-jpa-repo-simple-test
 *             단위 테스트 https://jiminidaddy.github.io/dev/2021/05/20/dev-spring-%EB%8B%A8%EC%9C%84%ED%85%8C%EC%8A%A4%ED%8A%B8-Repository/
 *             after, before 어노테이션 https://mimah.tistory.com/entry/Spring-Boot-AfterEach-BeforeEach-%EC%98%88%EC%A0%9C
 *                                    https://bcp0109.tistory.com/245
 *             DataJpaTest 정리 https://insanelysimple.tistory.com/338
 *                             https://webcoding-start.tistory.com/20
 *             Transactional 정리 https://imiyoungman.tistory.com/9
 *
 * author : 임현영
 * date : 2023.06.01
 **/
@DataJpaTest
class UserRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @BeforeEach
//    public void makeUser(){
//        User user=new User();
//        user.setUserId("zida");
//    }

    @Test
    void save() {
        User user= User.builder().userId("zida").build();

        logger.info("###### save User 주소값 {} :",user.hashCode());
        userRepository.save((user));
        User result = userRepository.findByUserId(user.getUserId());
        assertThat(result).isEqualTo(user);
    }

    @Test
    void findBySeq() {
        User user= User.builder().userId("zida").build();

        logger.info("###### findBySeq User 주소값 {} :",user.hashCode());

        userRepository.save(user);
        long seq = user.getSeq();
        User result = userRepository.findBySeq(seq);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void findAll() {
        User user=User.builder().userId("zida").build();
        userRepository.save(user);
        logger.info("###### findAll User 주소값 {} :",user.hashCode());

        User user2=User.builder().userId("zida2").build();
        userRepository.save(user2);

        User user3=User.builder().userId("zida3").build();
        userRepository.save(user3);

        List<User> result = userRepository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }
}
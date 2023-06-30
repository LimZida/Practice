package practice.toyproject.util.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import practice.toyproject.util.custom.entity.PrincipalDetails;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.repository.UserRepository;

//Optional https://mangkyu.tistory.com/70
//        *             메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 좋다.
//        *             즉, Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.

//https://velog.io/@kyu9610/Spring-Security-4.-Spring-Security-%EB%A1%9C%EA%B7%B8%EC%9D%B8
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("############ userId {}",userId);
        log.info("################### filter로 들어온 데이터 결과 {}",userRepository.findUserByUserId(userId).toString());

        User userEntity = userRepository.findUserByUserId(userId);
        if(userEntity == null){
            return null;
        }
        PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
        log.info("################ 후..{}", principalDetails.getAuthorities());
        log.info("################ 후..{}", principalDetails.getUsername());
        log.info("################ 후..{}", principalDetails.getPassword());

        return principalDetails;
    }
}

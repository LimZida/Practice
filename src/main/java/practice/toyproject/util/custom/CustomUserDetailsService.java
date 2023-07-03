package practice.toyproject.util.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import practice.toyproject.util.custom.entity.UserDetailsImpl;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.repository.UserRepository;
/**
 * title : CustomUserDetailsService
 *
 * description : UserDetailsService interface 구현체, 기존 loadUserByUsername 커스텀
 *
 * reference : loadUserByUsername 개발방식 차용: https://velog.io/@kyu9610/Spring-Security-4.-Spring-Security-%EB%A1%9C%EA%B7%B8%EC%9D%B8
 *
 *
 * author : 임현영
 * date : 2023.06.30
 **/
//https://velog.io/@kyu9610/Spring-Security-4.-Spring-Security-%EB%A1%9C%EA%B7%B8%EC%9D%B8
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User userEntity = userRepository.findUserByUserId(userId);
        if(userEntity == null){
            return null;
        }
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(userEntity);

        return userDetailsImpl;
    }
}

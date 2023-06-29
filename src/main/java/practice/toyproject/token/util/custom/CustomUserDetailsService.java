package practice.toyproject.token.util.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import practice.toyproject.token.util.custom.entity.PrincipalDetails;
import practice.toyproject.user.entity.User;
import practice.toyproject.user.repository.UserRepository;

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
        log.info("################### 씨발 {}",userRepository.findUserByUserId(userId));

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

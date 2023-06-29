package practice.toyproject.token.util.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import practice.toyproject.user.repository.UserRepository;

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

        return  userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " 사용자를 찾을 수 없습니다."));
    }
}

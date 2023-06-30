package practice.toyproject.util.custom.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import practice.toyproject.user.entity.User;

import java.util.ArrayList;
import java.util.Collection;
/**
 * title : entity
 *
 * description : T_TOKEN 테이블 컬럼 매핑용 entity
 *
 * reference : 생성자 https://kadosholy.tistory.com/91
 * 롬복  https://www.daleseo.com/lombok-popular-annotations/
 * 엔티티 https://choiseonjae.github.io/jpa/jpa-%EA%B8%B0%EB%B3%B8%ED%82%A4%EC%A0%84/
 * 엔티티 setter 쓰지않는 이유 https://velog.io/@langoustine/setter%EB%A5%BC-%EC%93%B0%EC%A7%80%EB%A7%90%EB%9D%BC%EA%B3%A0
 *                          https://velog.io/@aidenshin/%EB%82%B4%EA%B0%80-%EC%83%9D%EA%B0%81%ED%95%98%EB%8A%94-JPA-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%9E%91%EC%84%B1-%EC%9B%90%EC%B9%99
 * 빌더 https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
 *
 * author : 임현영
 * date : 2023.06.27
 **/
public class PrincipalDetails implements UserDetails {
    private User user; // 컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는곳.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }


    // User 의 password 리턴
    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 사이트 내에서 1년동안 로그인을 안하면 휴먼계정을 전환을 하도록 하겠다.
        // -> loginDate 타입을 모아놨다가 이 값을 false로 return 해버리면 된다.
        return true;
    }
}

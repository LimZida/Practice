package practice.toyproject.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import practice.toyproject.util.JWT.JwtProvider;
/**
 * title : SecurityConfig
 *
 * description : SecurityConfig 커스텀, Spring security 요청 접근 제어 configure 총괄
 *
 * reference : Spring security + JWT : https://do5do.tistory.com/14
 *             ,https://velog.io/@suhongkim98/Spring-Security-JWT%EB%A1%9C-%EC%9D%B8%EC%A6%9D-%EC%9D%B8%EA%B0%80-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
 *             Spring security 아키텍처 구조 및 흐름 : https://twer.tistory.com/entry/Security-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%9D%98-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%EA%B5%AC%EC%A1%B0-%EB%B0%8F-%ED%9D%90%EB%A6%84
 *                                                : https://velog.io/@younghoondoodoom/Spring-Security%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%9D%B8%EC%A6%9D-%EA%B5%AC%EC%A1%B0
 * author : 임현영
 * date : 2023.06.30
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;
//    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider, CorsFilter corsFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtProvider = jwtProvider;
//        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 서버에 인증정보를 저장하지 않기 때문에(stateless, rest api) csrf를 추가할 필요가 없다.
                .httpBasic().disable() // 기본 인증 로그인 사용하지 않음. (rest api)
                .cors()

                // session 설정 -> stateless(사용하지 않음)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // request permission
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll() // index.html
                .antMatchers("/shop/login").permitAll() // 로그인 경로
                .antMatchers("/shop/signup").permitAll() // 회원가입 경로는 인증없이 호출 가능
                .antMatchers("/shop/upload").permitAll()
                .antMatchers("/shop/download").permitAll()
                .anyRequest().authenticated() // 나머지 경로는 jwt 인증 해야함

                // exception handling
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // jwt filter -> 인증 정보 필터링 전에(filterBefore) 필터
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}

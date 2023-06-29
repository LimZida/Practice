package practice.toyproject.token.util.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import practice.toyproject.token.util.JWT.JwtFilter;
import practice.toyproject.token.util.JWT.JwtProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtProvider jwtProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
//        this.customUserDetailsService = customUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService); // customUserDetailsService 등록
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 서버에 인증정보를 저장하지 않기 때문에(stateless, rest api) csrf를 추가할 필요가 없다.
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
//                .anyRequest().authenticated() // 나머지 경로는 jwt 인증 해야함

                // exception handling
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // jwt filter -> 인증 정보 필터링 전에(filterBefore) 필터
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

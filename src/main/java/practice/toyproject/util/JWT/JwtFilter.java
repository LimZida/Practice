package practice.toyproject.util.JWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * title : JwtFilter
 *
 * description : Servlet에서 요청받기 전 Spring security filter단에서 JWT 검증
 *
 * reference : Spring security + JWT : https://do5do.tistory.com/14
 *             ,https://velog.io/@suhongkim98/Spring-Security-JWT%EB%A1%9C-%EC%9D%B8%EC%A6%9D-%EC%9D%B8%EA%B0%80-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
 *
 * author : 임현영
 * date : 2023.06.30
 **/
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request, "Authorization");

        // access token 검증
        if (StringUtils.hasText(accessToken) && jwtProvider.validateToken(accessToken) == JwtCode.ACCESS) {
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication); // security context에 인증 정보 저장
        } else if (StringUtils.hasText(accessToken) && jwtProvider.validateToken(accessToken) == JwtCode.EXPIRED) {
            log.info("Access token expired");

            String refreshToken = null;
            if (StringUtils.hasText(request.getHeader("Auth"))) { // Auth에는 userId가 담겨 있음
                String userId = request.getHeader("Auth");
                refreshToken = jwtProvider.getRefreshToken(userId); // userId로 refreshToken 조회
            }

            // refresh token 검증
            if (StringUtils.hasText(refreshToken) && jwtProvider.validateToken(refreshToken) == JwtCode.ACCESS) {
                // access token 재발급
                Authentication authentication = jwtProvider.getAuthentication(refreshToken);
                String newAccessToken = jwtProvider.generateAccessToken(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                response.setHeader(HttpHeaders.AUTHORIZATION, newAccessToken);
                log.info("Reissue access token");
            } else if(StringUtils.hasText(refreshToken) && jwtProvider.validateToken(refreshToken) == JwtCode.EXPIRED){
                response.setHeader(HttpHeaders.AUTHORIZATION, "refresh token expired");
                log.info("refresh token expired! please redirect to login");
            }
        }
        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

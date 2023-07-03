package practice.toyproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * title : LogInfoAop
 *
 * description : AOP 적용, 스프링 빈 등록
 *
 * reference :  로그 aop : https://velog.io/@dhk22/Spring-AOP-%EA%B0%84%EB%8B%A8%ED%95%9C-AOP-%EC%A0%81%EC%9A%A9-%EC%98%88%EC%A0%9C-Logging
 *
 *
 *
 * author : 임현영
 * date : 2023.07.03
 **/
@Slf4j
@Aspect
@Component
public class LogInfoAop {

    // com.aop.controller 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* practice.toyproject..*(..))")
    private void cut(){}

    // Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= 요청 메소드명 {} =======", method.getName());

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("파라미터가 없습니다.");
        for (Object arg : args) {
            log.info("요청 파라미터 타입 = {}", arg.getClass().getSimpleName());
            log.info("요청 파라미터 값 = {}", arg);
            log.info(" ");
        }
    }

    // Poincut에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= 리턴 메소드명 {} =======", method.getName());

        log.info("리턴 파라미터 타입 = {}", returnObj.getClass().getSimpleName());
        log.info("리턴 파라미터 값 = {}", returnObj);
        log.info(" ");
    }

    // JoinPoint로 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
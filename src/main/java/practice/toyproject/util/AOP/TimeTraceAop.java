package practice.toyproject.util.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
/**
 * title : TimeTraceAop
 *
 * description : 함수 실행 시간에 대해 AOP 적용
 *
 * reference :  시간 aop : https://hseungyeon.tistory.com/349
 *              aop 어노테이션 : https://programforlife.tistory.com/107 , https://code-lab1.tistory.com/193
 *              프록시 패턴 : https://velog.io/@newtownboy/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4-%ED%94%84%EB%A1%9D%EC%8B%9C%ED%8C%A8%ED%84%B4Proxy-Pattern
 *
 * author : 임현영
 * date : 2023.07.03
 **/
@Slf4j
@Aspect
@Component
public class TimeTraceAop {
    // 공통관심사항을 적용할 곳(practice.toyproject 패키지 하위 모두) 타겟팅
    @Around("execution(* practice.toyproject..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();    // 시작 시각

//        log.info("####### START : {}",joinPoint.toString());
        try {
            return joinPoint.proceed();
        } catch (Exception e){
            log.info("TimeTraceAop 적용 중 오류 발생 {}",e);
            return new Exception("TimeTraceAop 적용 중 오류 발생"+ e);
        } finally {
            long finish = System.currentTimeMillis();   // 종료 시각
            long timeMs = finish - start;   // 호출 시간

            log.info("수행시간 : {}", timeMs + "ms");
            log.info(" ");
        }
    }
}
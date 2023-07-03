package practice.toyproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
/**
 * title : TimeTraceAop
 *
 * description : AOP 적용, 스프링 빈 등록
 *
 * reference :  시간 aop : https://hseungyeon.tistory.com/349
 *              aop 어노테이션 : https://programforlife.tistory.com/107
 *
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
        } finally {
            long finish = System.currentTimeMillis();   // 종료 시각
            long timeMs = finish - start;   // 호출 시간

            log.info("수행시간 : {}",joinPoint.toString()+" "+ timeMs + "ms");
        }
    }
}
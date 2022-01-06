package com.anitop.util.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Aspect
@Component
public class LogAdvice {

	@Around("execution(* com.anitop.*.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		Object result = null;
		
		/* 실행 이전 처리 -- 넘어오는 데이터 출력 */
		// 시작 시간 저장
		long start = System.currentTimeMillis();
		log.info("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ service Log ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		log.info("실행 서비스 : " + pjp.getTarget());
		log.info("넘어가는 데이터 : " + Arrays.toString(pjp.getArgs()));
		
		// aop가 여러개인 경우 
		try { // 다음 aop로 이동
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		/* 실행 이후 처리 -- 실행 결과 데이터 출력 */
		// 종료 시간 저장
		long end = System.currentTimeMillis();
		
		log.info("TIME : " + ((end - start) / 1000.0) + "초");
		log.info("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		return result;
	}
	
}

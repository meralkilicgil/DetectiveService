package com.meri.murdermysterygame.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.meri.murdermysterygame..*.*(..))")
    public void hello(JoinPoint joinPoint){
        String source = joinPoint.getSignature().toShortString();
        logger.error("Hello world from {}", source);
    }


    @Around("@annotation(com.meri.murdermysterygame.aop.LogAnnotation)")
    public Object logAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info(joinPoint.getSignature() + " executed in " + (endTime - startTime) + "ms");
        return proceed;
    }

}

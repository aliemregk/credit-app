package com.credit.app.core.aspects.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("execution(* com.credit.app.business.concretes.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        try {
            log.info("Log - Calling the method: " + methodName + ", from class: " + className);
            Object methodReturn = joinPoint.proceed();
            log.info("Log - Method executed successfully.");
            return methodReturn;
        } catch (Exception e) {
            log.error("Log - Exception occured while executing: " + methodName + ", from class: " + className);
            log.error("Exception: " + e);
            throw e;
        }
    }
}

package com.homework.aop.logging;

import com.homework.aop.people.Visitor;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;




/**
 * Класс аспект с использованием аннотаций.
 */

@Aspect
public class AnnAopLogger {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AnnAopLogger.class));

    @Before("execution(* com.homework.aop.people.Vahtersha.*(..))")
    public void logBefore() {
        LOGGER.info("Method is calling...");
    }

    @After("execution(* com.homework.aop.people.Vahtersha.*(..))")
    public void logAfter() {
        LOGGER.info("Method finished.");
    }

    @AfterReturning("execution(* com.homework.aop.people.Vahtersha.*(..))")
    public void logAfterSuccess() {
        LOGGER.info("Method successfully called.");
    }

    @AfterThrowing(pointcut = "execution(* com.homework.aop.people.Vahtersha.*(..))", throwing = "error")
    public void logAfterException(Throwable error) {
            LOGGER.error("Method throws an exception! " + error.getMessage());

    }

    @Around("execution(* com.homework.aop.people.Vahtersha.newVisitor(..)) " +
            "&& args(visitor)" +
            "|| execution(* com.homework.aop.people.Vahtersha.leaveVisitor(..)) " +
            "&& args(visitor)")
    public void logAround(ProceedingJoinPoint joinPoint, Visitor visitor) {
        String methodName = getMethodName(joinPoint);
        LOGGER.info("Method \"" + methodName + "\" is calling with argument = " + visitor + "...");
        try {
            joinPoint.proceed();
            LOGGER.info("Method \"" + methodName + "\" called.");
        } catch (Throwable throwable) {
            LOGGER.error("Method \"" + methodName + "\" throws an exception: " + throwable.getMessage());
        }
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }
}

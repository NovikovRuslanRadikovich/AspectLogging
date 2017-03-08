package com.homework.aop.logging;

import com.homework.aop.people.Visitor;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


/**
 * Класс аспект, который используется в XML конфигурации.
 */
public class XmlAopLogger {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(XmlAopLogger.class));

    public void logBefore() {
        LOGGER.info("Method is calling...");
    }

    public void logAfter() {
        LOGGER.info("Method finished.");
    }

    public void logAfterSuccess() {
        LOGGER.info("Method successfully called.");
    }

    public void logAfterException() {
       LOGGER.error("Method throws an exception!");
    }

    public void logAround(ProceedingJoinPoint joinPoint, Visitor visitor) {
        String methodName = getMethodName(joinPoint);
        LOGGER.info("Method \"" + methodName + "\" is calling with argument = " + visitor + "...");
        try {
            joinPoint.proceed(new Object[]{visitor});
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

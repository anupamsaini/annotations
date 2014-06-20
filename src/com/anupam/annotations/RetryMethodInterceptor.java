package com.anupam.annotations;

import java.util.Set;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.common.collect.Sets;

public class RetryMethodInterceptor implements MethodInterceptor {

  private static final Logger logger = Logger.getLogger(RetryMethodInterceptor.class.getName());

  @Override
  public Object invoke(MethodInvocation methodInvocator) throws Throwable {
    Retry retryAnnotation = methodInvocator.getMethod().getAnnotation(Retry.class);
    Set<Class<? extends Throwable>> retriableExceptions =
        Sets.newHashSet(retryAnnotation.retryOnExceptions());

    String className = methodInvocator.getThis().getClass().getCanonicalName();
    String methodName = methodInvocator.getMethod().getName();

    int tryCount = 0;
    while (true) {
      try {
        return methodInvocator.proceed();
      } catch (Throwable ex) {
        tryCount++;
        boolean isExceptionInAllowedList = isRetriableException(retriableExceptions, ex.getClass());
        if (!isExceptionInAllowedList) {
          System.out.println(String.format(
              "Exception not in retry list for class: %s - method: %s - retry count: %s",
              className, methodName, tryCount));
          throw ex;
        } else if (isExceptionInAllowedList && tryCount > retryAnnotation.maxTries()) {
          System.out.println(String
                  .format(
                      "Exhausted retries, rethrowing exception for class: %s - method: %s - retry count: %s",
                      className, methodName, tryCount));
          throw ex;
        }
        System.out.println(String.format("Retrying for class: %s - method: %s - retry count: %s",
            className, methodName, tryCount));
      }
    }
  }

  private boolean isRetriableException(Set<Class<? extends Throwable>> allowedExceptions,
      Class<? extends Throwable> caughtException) {
    for (Class<? extends Throwable> look : allowedExceptions) {
      // Only compare the class names we do not want to compare superclass so Class#isAssignableFrom
      // can't be used.
      if (caughtException.getCanonicalName().equalsIgnoreCase(look.getCanonicalName())) {
        return true;
      }
    }
    return false;
  }
}

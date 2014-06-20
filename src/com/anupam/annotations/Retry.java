package com.anupam.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Attempts to retry a method in case of exception. User's must specify the exceptions they
 * wan't to retry through <li>applyOnException</li>.
 *
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Retry {

  int maxTries() default 0;

  /**
   * Attempt retry if one of the following exceptions is thrown.
   * @return array of applicable exceptions
   */
  Class<? extends Throwable> [] retryOnExceptions() default {Throwable.class};
}

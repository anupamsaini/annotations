package com.anupam.annotations;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class RetryModule extends AbstractModule {
  @Override
  protected void configure() {
    bindInterceptor(Matchers.any(), Matchers.annotatedWith(Retry.class),
        new RetryMethodInterceptor());
  }
}

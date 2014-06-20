package com.anupam.annotations;

import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;

/**
 * Test case for {@link RetryMethodInterceptor}.
 * 
 */
public class RetryMethodInterceptorTest extends TestCase {

  private static final int MAX_TRIES = 3;

  Injector injector;

  @Before
  public void setUp() throws Exception {
    injector = Guice.createInjector(new AbstractModule() {

      @Override
      protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Retry.class),
            new RetryMethodInterceptor());
      }
    });
  }

  static class Test1 implements Runnable {
    final AtomicInteger counter = new AtomicInteger();

    @Override
    @Retry
    public void run() {
      counter.getAndIncrement();
    }
  }

  @Test
  public void test_retryWithoutException() {
    Test1 testInstance = injector.getInstance(Test1.class);
    testInstance.run();
    assertTrue(testInstance.counter.get() == 1);
  }

  static class Test2 implements Runnable {
    final AtomicInteger counter = new AtomicInteger();

    @Override
    @Retry(maxTries = MAX_TRIES)
    public void run() {
      counter.getAndIncrement();
      throw new IllegalStateException();
    }
  }

  @Test
  public void test_retryWithExceptionNotInApplicableList() {
    Test2 testInstance = injector.getInstance(Test2.class);
    try {
      testInstance.run();
      fail();
    } catch (Exception e) {
      assertEquals(e.getClass(), IllegalStateException.class);
      assertTrue(testInstance.counter.get() == 1);
    }
  }

  static class Test3 implements Runnable {
    final AtomicInteger counter = new AtomicInteger();

    @Override
    @Retry(maxTries = MAX_TRIES,retryOnExceptions ={IllegalStateException.class, IllegalStateException.class})
    public void run() {
      counter.getAndIncrement();
      throw new IllegalStateException();
    }
  }

  @Test
  public void test_retryWithExceptionInApplicableList() {
    Test3 testInstance = injector.getInstance(Test3.class);
    try {
      testInstance.run();
      fail();
    } catch (Exception e) {
      assertEquals(e.getClass(), IllegalStateException.class);
      assertTrue(testInstance.counter.get() == MAX_TRIES + 1);
    }
  }
}

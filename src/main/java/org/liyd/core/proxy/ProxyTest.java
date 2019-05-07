package org.liyd.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;


public class ProxyTest {
  public static void main(String[] args) {
    Object[] elements = new Object[1000];

    IntStream.range(0, elements.length).forEachOrdered(i -> {
      Integer value = i + 1;
      InvocationHandler handler = new TraceHandler(value);
      Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
      elements[i] = proxy;
    });

    // construct a random integer
    Integer key = new Random().nextInt(elements.length) + 1;

    // search for the key
    int result = Arrays.binarySearch(elements, key);

    // print match if found
    if (result > 0) System.out.println(elements[result]);
  }
}

/**
 * 使用代理和InvocationHandler跟踪方法调用，
 * 并且定义了一个TraceHandler包装器存储包装的对象。
 */
class TraceHandler implements InvocationHandler {

  private Object target;

  public TraceHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // print method name and parameters
    System.out.print(target);
    System.out.print("." + method.getName() + "(");

    if (args != null) IntStream.range(0, args.length).forEachOrdered(i -> {
      System.out.print(args[i]);
      if (i < args.length - 1) {
        System.out.print(", ");
      }
    });

    System.out.println(")");

    // invoke actual method
    return method.invoke(target, args);
  }
}

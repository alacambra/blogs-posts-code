package tech.lacambra.reflexion;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflexionTest {

  @Test
  public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Method methodA = MyClass.class.getMethod("methodA");
    MyClass myClassInstance = new MyClass();

    methodA.invoke(myClassInstance);

    Method privateMethodB = MyClass.class.getDeclaredMethod("privateMethodB");
    privateMethodB.setAccessible(true);
    privateMethodB.invoke(myClassInstance);

    String result = (String) myClassInstance.getClass().getMethod("getStr", String.class).invoke(myClassInstance, "toAppend");

    assertEquals("toAppendsome value", result);

  }

  public <T> T getValue(String methodName, Object instance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    return (T) instance.getClass().getMethod(methodName).invoke(instance);
  }
}




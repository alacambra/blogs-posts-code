package tech.lacambra.reflexion;

public class MyClass {

  private String str = "some value";

  public void methodA() {

  }

  private void privateMethodB() {

  }

  public String getStr() {
    return str;
  }

  public String getStr(String append) {
    return append + str;
  }
}

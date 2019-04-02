package tech.lacambla.blog.examples.simple_statemachine;

public interface StateObject {

  Object getState();
  void setState(Object target);

}

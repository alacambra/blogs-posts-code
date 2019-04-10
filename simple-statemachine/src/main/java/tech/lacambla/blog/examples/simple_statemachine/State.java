package tech.lacambla.blog.examples.simple_statemachine;

public interface State {
  void onState(StateObject stateObject);
  String getName();
}
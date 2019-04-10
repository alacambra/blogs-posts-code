package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;

public class InitState extends ValidatedState {
  @Override
  public String getName() {
    return "INIT";
  }

  @Override
  public void onState(StateObject stateObject) {
    stateObject.setState(getName());
  }
}

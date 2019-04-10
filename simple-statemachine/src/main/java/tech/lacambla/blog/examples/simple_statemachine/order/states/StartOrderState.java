package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;

public class StartOrderState extends ValidatedState {
  @Override
  public String getName() {
    return "START_ORDER";
  }

  @Override
  public void onState(StateObject stateObject) {
    stateObject.setState(getName());
  }
}

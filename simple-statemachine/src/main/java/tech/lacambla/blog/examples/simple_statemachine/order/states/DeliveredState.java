package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;

public class DeliveredState extends ValidatedState {
  @Override
  public String getName() {
    return "DELIVERED";
  }

  @Override
  public void onState(StateObject stateObject) {
    stateObject.setState(getName());
  }
}

package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.State;
import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;

public class DispatchedState extends ValidatedState {
  @Override
  public String getName() {
    return "DISPATCHED";
  }

  @Override
  public void onState(StateObject stateObject) {
    stateObject.setState(getName());
  }
}
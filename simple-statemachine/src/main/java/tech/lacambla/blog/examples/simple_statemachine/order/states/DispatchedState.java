package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.State;
import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;
import tech.lacambla.blog.examples.simple_statemachine.order.Order;
import tech.lacambla.blog.examples.simple_statemachine.order.ValidationGroups;

public class DispatchedState extends ValidatedState {
  @Override
  public String getName() {
    return "DISPATCHED";
  }

  @Override
  public void enterState(Order stateObject) {
    stateObject.setState(getName());
  }

  @Override
  public Class[] getValidationGroups() {
    return new Class[]{ValidationGroups.Dispatched.class};
  }
}
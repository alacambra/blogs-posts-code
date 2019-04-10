package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;
import tech.lacambla.blog.examples.simple_statemachine.order.Order;
import tech.lacambla.blog.examples.simple_statemachine.order.ValidationGroups;

public class StartOrderState extends ValidatedState {
  @Override
  public String getName() {
    return "START_ORDER";
  }

  @Override
  public void enterState(Order stateObject) {
    stateObject.setState(getName());
  }

  @Override
  public Class[] getValidationGroups() {
    return new Class[]{ValidationGroups.Start.class};
  }
}

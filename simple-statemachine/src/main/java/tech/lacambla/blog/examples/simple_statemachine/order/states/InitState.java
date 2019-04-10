package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;
import tech.lacambla.blog.examples.simple_statemachine.order.Order;
import tech.lacambla.blog.examples.simple_statemachine.order.ValidationGroups;

import java.util.UUID;

public class InitState extends ValidatedState {
  @Override
  public String getName() {
    return "INIT";
  }

  @Override
  public void enterState(Order stateObject) {
    stateObject.setState(getName());
  }


  @Override
  public Class[] getValidationGroups() {
    return new Class[]{ValidationGroups.Init.class};
  }
}

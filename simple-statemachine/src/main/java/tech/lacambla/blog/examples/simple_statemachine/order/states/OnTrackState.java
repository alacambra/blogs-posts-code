package tech.lacambla.blog.examples.simple_statemachine.order.states;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.ValidatedState;
import tech.lacambla.blog.examples.simple_statemachine.order.Order;
import tech.lacambla.blog.examples.simple_statemachine.order.ValidationGroups;

public class OnTrackState extends ValidatedState {
  @Override
  public String getName() {
    return "ON_TRACK";
  }

  @Override
  public void enterState(Order stateObject) {
    stateObject.setState(getName());
  }

  @Override
  public Class[] getValidationGroups() {
    return new Class[]{ValidationGroups.OnTrack.class};
  }
}
package tech.lacambla.blog.examples.simple_statemachine;


import java.util.ArrayList;
import java.util.List;

public class StateMachine {

  public List<Transition> transitions;

  public StateMachine(List<Transition> transitions) {
    this.transitions = new ArrayList<>(transitions);
  }

  public void trigger(Object event, StateObject stateObject) {

    Object target = transitions
        .stream()
        .filter(t -> t.getEvent().equals(event))
        .filter(t -> t.getSource().equals(stateObject.getState()))
        .findAny()
        .orElseThrow(() -> new InvalidTransitionException(event, stateObject.getState()))
        .getTarget();

    stateObject.setState(target);
  }

}

package tech.lacambla.blog.examples.simple_statemachine;


import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateMachine {

  public List<Transition> transitions;

  public StateMachine(List<Transition> transitions) {
    this.transitions = new ArrayList<>(transitions);
  }

  public Optional<ConstraintViolationException> trigger(Object event, StateObject stateObject) {

    Object state = stateObject.getState();

    Optional<ConstraintViolationException> r = transitions
        .stream()
        .filter(t -> t.getEvent().equals(event))
        .filter(t -> t.getSource().getName().equals(stateObject.getState()))
        .findAny()
        .orElseThrow(() -> new InvalidTransitionException(event, stateObject.getState()))
        .getTarget().onState(stateObject);

    //Simulates a roll-back in case of error
    r.ifPresent(ex -> stateObject.setState(state));

    return r;
  }

}

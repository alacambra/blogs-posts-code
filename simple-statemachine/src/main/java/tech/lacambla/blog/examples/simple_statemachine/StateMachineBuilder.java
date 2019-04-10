package tech.lacambla.blog.examples.simple_statemachine;

import java.util.ArrayList;
import java.util.List;

public class StateMachineBuilder {

  private List<Transition> transitions;

  public StateMachineBuilder() {
    transitions = new ArrayList<>();
  }

  public TransitionBuilder beginTransition() {
    return new TransitionBuilder();
  }

  public StateMachine done() {
    return new StateMachine(transitions);
  }

  private void addTransition(Transition transition) {
    transitions.add(transition);
  }

  public class TransitionBuilder {
    private State source;
    private State target;
    private Object event;

    private TransitionBuilder() {
    }

    public TransitionBuilder fromState(State source) {
      this.source = source;
      return this;
    }

    public TransitionBuilder goToState(State target) {
      this.target = target;
      return this;
    }

    public TransitionBuilder onEvent(Object event) {
      this.event = event;
      return this;
    }

    public TransitionBuilder addAndBeginTransition() {
      Transition transition = new Transition(source, target, event);
      StateMachineBuilder.this.addTransition(transition);
      return new TransitionBuilder();
    }

    public StateMachine done() {
      Transition transition = new Transition(source, target, event);
      StateMachineBuilder.this.addTransition(transition);
      return StateMachineBuilder.this.done();
    }

  }
}

package tech.lacambla.blog.examples.simple_statemachine;

public class OrderStateMachineFactory {

  public StateMachine create() {

    return new StateMachineBuilder()

        .beginTransition()

        .fromState(State.INIT)
        .goToState(State.BOOKED)
        .onEvent(Event.START_ORDER)

        .addAndBeginTransition()

        .fromState(State.BOOKED)
        .goToState(State.DISPATCHED)
        .onEvent(Event.DISPATCH)

        .addAndBeginTransition()

        .fromState(State.DISPATCHED)
        .goToState(State.ON_TRACK)
        .onEvent(Event.SEND)

        .addAndBeginTransition()

        .fromState(State.ON_TRACK)
        .goToState(State.DELIVERED)
        .onEvent(Event.DELIVER)

        .done();

  }


}

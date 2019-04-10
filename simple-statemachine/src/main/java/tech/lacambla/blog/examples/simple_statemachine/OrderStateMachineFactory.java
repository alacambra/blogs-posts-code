package tech.lacambla.blog.examples.simple_statemachine;

import tech.lacambla.blog.examples.simple_statemachine.order.states.*;

public class OrderStateMachineFactory {

  public StateMachine create() {

    InitState initState = new InitState();
    BookedState bookedState = new BookedState();
    DispatchedState dispatchedState = new DispatchedState();
    OnTrackState onTrackState = new OnTrackState();
    DeliveredState deliveredState = new DeliveredState();

    return new StateMachineBuilder()

        .beginTransition()

        .fromState(initState)
        .goToState(bookedState)
        .onEvent(Event.START_ORDER)

        .addAndBeginTransition()

        .fromState(bookedState)
        .goToState(dispatchedState)
        .onEvent(Event.DISPATCH)

        .addAndBeginTransition()

        .fromState(dispatchedState)
        .goToState(onTrackState)
        .onEvent(Event.SEND)

        .addAndBeginTransition()

        .fromState(onTrackState)
        .goToState(deliveredState)
        .onEvent(Event.DELIVER)

        .done();
  }
}
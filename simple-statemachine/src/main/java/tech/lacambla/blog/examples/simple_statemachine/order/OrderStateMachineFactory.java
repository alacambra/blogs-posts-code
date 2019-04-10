package tech.lacambla.blog.examples.simple_statemachine.order;

import tech.lacambla.blog.examples.simple_statemachine.StateMachine;
import tech.lacambla.blog.examples.simple_statemachine.StateMachineBuilder;
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

        .onEvent(Event.START_ORDER)
        .fromState(initState)
        .goToState(bookedState)

        .addAndBeginTransition()

        .onEvent(Event.DISPATCH)
        .fromState(bookedState)
        .goToState(dispatchedState)

        .addAndBeginTransition()

        .onEvent(Event.SEND)
        .fromState(dispatchedState)
        .goToState(onTrackState)

        .addAndBeginTransition()

        .onEvent(Event.DELIVER)
        .fromState(onTrackState)
        .goToState(deliveredState)

        .done();
  }
}
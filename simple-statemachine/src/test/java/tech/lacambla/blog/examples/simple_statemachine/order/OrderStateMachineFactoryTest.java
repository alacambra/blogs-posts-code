package tech.lacambla.blog.examples.simple_statemachine.order;

import org.junit.jupiter.api.Test;
import tech.lacambla.blog.examples.simple_statemachine.StateMachine;
import tech.lacambla.blog.examples.simple_statemachine.order.states.BookedState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.DeliveredState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.DispatchedState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.OnTrackState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStateMachineTest {

  @Test
  void create() {

    StateMachine stateMachine = new OrderStateMachineFactory().create();
    Order order = new Order();

    stateMachine.trigger(Event.START_ORDER, order);
    assertEquals(new BookedState().getName(), order.getState());

    stateMachine.trigger(Event.DISPATCH, order);
    assertEquals(new DispatchedState().getName(), order.getState());

    stateMachine.trigger(Event.SEND, order);
    assertEquals(new OnTrackState().getName(), order.getState());

    stateMachine.trigger(Event.DELIVER, order);
    assertEquals(new DeliveredState().getName(), order.getState());
  }
}
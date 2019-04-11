package tech.lacambla.blog.examples.simple_statemachine.order;

import org.junit.jupiter.api.Test;
import tech.lacambla.blog.examples.simple_statemachine.StateMachine;
import tech.lacambla.blog.examples.simple_statemachine.order.states.BookedState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.DeliveredState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.DispatchedState;
import tech.lacambla.blog.examples.simple_statemachine.order.states.OnTrackState;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderStateMachineTest {

  @Test
  void create() {

    StateMachine stateMachine = new OrderStateMachineFactory().create();
    Order order = new Order();

    Optional<ConstraintViolationException> r = stateMachine.trigger(Event.START_ORDER, order);

    assertFalse(r.isEmpty());
    System.out.println("1:" + r.get().getMessage());

    order.setId("OrderId");
    order.setItemId("ItemId");
    r = stateMachine.trigger(Event.START_ORDER, order);

    assertTrue(r.isEmpty());
    assertEquals(new BookedState().getName(), order.getState());

    r = stateMachine.trigger(Event.DISPATCH, order);
    assertFalse(r.isEmpty());
    System.out.println("2:" + r.get().getMessage());

    order.setInvoiceRef("InvoiceRef");
    order.setAddress("Major Str. PLZ 122 Berlin");

    r = stateMachine.trigger(Event.DISPATCH, order);
    assertTrue(r.isEmpty());
    assertEquals(new DispatchedState().getName(), order.getState());

    r = stateMachine.trigger(Event.SEND, order);
    assertTrue(r.isEmpty());
    assertEquals(new OnTrackState().getName(), order.getState());

    r = stateMachine.trigger(Event.DELIVER, order);
    assertTrue(r.isEmpty());
    assertEquals(new DeliveredState().getName(), order.getState());
  }
}
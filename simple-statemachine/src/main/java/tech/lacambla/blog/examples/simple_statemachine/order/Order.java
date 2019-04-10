package tech.lacambla.blog.examples.simple_statemachine.order;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.order.states.InitState;

import javax.validation.constraints.NotNull;

public class Order implements StateObject {

  @NotNull
  private String state;

  @NotNull(groups = {ValidationGroups.Booked.class, ValidationGroups.Delivered.class, ValidationGroups.Dispatched.class, ValidationGroups.OnTrack.class})
  private String id;
  private String itemId;

  public Order() {
    state = new InitState().getName();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  @Override
  public Object getState() {
    return state;
  }

  @Override
  public void setState(Object target) {
    state = (String) target;
  }
}

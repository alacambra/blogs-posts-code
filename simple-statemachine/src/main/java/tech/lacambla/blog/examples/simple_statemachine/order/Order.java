package tech.lacambla.blog.examples.simple_statemachine.order;

import tech.lacambla.blog.examples.simple_statemachine.StateObject;
import tech.lacambla.blog.examples.simple_statemachine.order.states.InitState;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Order implements StateObject {

  @NotNull
  private String state;

  @NotNull(groups = {ValidationGroups.Booked.class, ValidationGroups.Delivered.class, ValidationGroups.Dispatched.class, ValidationGroups.OnTrack.class})
  private String id;

  @NotEmpty(groups = {ValidationGroups.Booked.class})
  private String itemId;

  @NotEmpty(groups = {ValidationGroups.Delivered.class, ValidationGroups.Dispatched.class, ValidationGroups.OnTrack.class})
  private String invoiceRef;

  @NotEmpty(groups = {ValidationGroups.Delivered.class, ValidationGroups.Dispatched.class, ValidationGroups.OnTrack.class})
  private String address;

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

  public void setState(String state) {
    this.state = state;
  }

  public void setInvoiceRef(String invoiceRef) {
    this.invoiceRef = invoiceRef;
  }

  public void setAddress(String address) {
    this.address = address;
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

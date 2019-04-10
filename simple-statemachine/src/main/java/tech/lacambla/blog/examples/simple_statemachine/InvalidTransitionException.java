package tech.lacambla.blog.examples.simple_statemachine;

public class InvalidTransitionException extends RuntimeException {

  private final Object source;
  private final Object event;

  public InvalidTransitionException(Object event, Object source) {
    super(String.format("Transition for event %s from source %s not found", String.valueOf(event), String.valueOf(source)));
    this.source = source;
    this.event = event;
  }

  public Object getSource() {
    return source;
  }

  public Object getEvent() {
    return event;
  }
}

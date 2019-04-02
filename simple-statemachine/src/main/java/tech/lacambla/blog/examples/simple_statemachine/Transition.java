package tech.lacambla.blog.examples.simple_statemachine;

import java.util.Objects;

public class Transition {

  private Object source;
  private Object target;
  private Object event;

  public Transition(Object source, Object target, Object event) {

    this.source = source;
    this.target = target;
    this.event = event;
  }

  public Object getSource() {
    return source;
  }

  public Object getTarget() {
    return target;
  }

  public Object getEvent() {
    return event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transition that = (Transition) o;
    return Objects.equals(source, that.source) &&
        Objects.equals(target, that.target) &&
        Objects.equals(event, that.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, target, event);
  }
}

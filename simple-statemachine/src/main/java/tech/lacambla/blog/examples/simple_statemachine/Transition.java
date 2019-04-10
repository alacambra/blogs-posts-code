package tech.lacambla.blog.examples.simple_statemachine;

import java.util.Objects;

public class Transition {

  private State source;
  private State target;
  private Object event;

  public Transition(State source, State target, Object event) {

    this.source = source;
    this.target = target;
    this.event = event;
  }

  public State getSource() {
    return source;
  }

  public State getTarget() {
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

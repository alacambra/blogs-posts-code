package tech.lacambla.blog.examples.simple_statemachine;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

public interface State {
  Optional<ConstraintViolationException> onState(StateObject stateObject);
  String getName();
}
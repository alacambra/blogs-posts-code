package tech.lacambla.blog.examples.simple_statemachine;

import tech.lacambla.blog.examples.simple_statemachine.order.Order;

import javax.validation.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ValidatedState implements State {

  private Validator validator;

  public ValidatedState() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Override
  public Optional<ConstraintViolationException> onState(StateObject stateObject) {
    enterState((Order) stateObject);
    Set<ConstraintViolation<StateObject>> violations = validator.validate(stateObject, getValidationGroups());
    if (!violations.isEmpty()) {
      return Optional.of(new ConstraintViolationException("Violations on state " + getName() + ". " + toString(violations), violations));
    }

    return Optional.empty();
  }

  public abstract void enterState(Order stateObject);

  public abstract Class[] getValidationGroups();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    State that = (State) o;
    return that.getName().equalsIgnoreCase(getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
    return constraintViolations.stream()
        .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
        .collect(Collectors.joining(", "));

  }
}
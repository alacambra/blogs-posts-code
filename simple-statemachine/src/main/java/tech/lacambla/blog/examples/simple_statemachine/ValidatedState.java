package tech.lacambla.blog.examples.simple_statemachine;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class ValidatedState implements State {

  private Validator validator;

  public ValidatedState() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Override
  public void onState(StateObject stateObject) {
    validator.validate(stateObject, getClass());
  }
}

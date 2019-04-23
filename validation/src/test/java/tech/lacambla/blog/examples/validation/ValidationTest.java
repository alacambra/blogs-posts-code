package tech.lacambla.blog.examples.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

class ValidationTest {

  private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = factory.getValidator();
  }

  @Test
  void validateSingleItem() {

    Item item = new Item(1, "MacbookPro", BigDecimal.ONE);
    Set<ConstraintViolation<Item>> violations = validator.validate(item);
    Assertions.assertTrue(violations.isEmpty());


    item = new Item(1, "", BigDecimal.ZERO);
    violations = validator.validate(item);
    Assertions.assertTrue(violations.isEmpty(), ObjectsPrinter.print(violations));

  }
}
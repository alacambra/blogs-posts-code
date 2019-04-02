package tech.lacambla.blog.examples.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

public class OrderService {

  private Validator validator;

  public OrderService(Validator validator) {
    this.validator = Objects.requireNonNull(validator);
  }

  public void addItem(Order order, Item item) {

    Set<ConstraintViolation<Item>> violations = validator.validate(item);

    if (!violations.isEmpty()) {
      throw new RuntimeException(ObjectsPrinter.print(violations));
    }

    order.addItem(item);
  }
}

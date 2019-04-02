package tech.lacambla.blog.examples.validation;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

public class Order {

  private Long id;
  private List<Item> items;
  private Validator validatorFactory;

  public Order(Validator validatorFactory) {
    this.validatorFactory = validatorFactory;
  }

  public Order() {
    items = new ArrayList<Item>();
  }

  public Order addItem(Item item) {
    validatorFactory.validate(item);
    items.add(item);
    return this;
  }
}

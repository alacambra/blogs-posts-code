package tech.lacambla.blog.examples.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Item {

  @NotNull
  private Integer id;

  @NotEmpty
  private String name;

  @Min(1)
  private BigDecimal price;

  public Item(Integer id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
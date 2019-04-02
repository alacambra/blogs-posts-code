package tech.lacambla.blog.examples.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectsPrinter {

  public static <T> String print(ConstraintViolation<T> violation) {

    return violation.toString();

  }

  public static <T> String print(Set<ConstraintViolation<T>> violations) {

    return violations.stream().map(ObjectsPrinter::print).collect(Collectors.joining(" | "));

  }

}

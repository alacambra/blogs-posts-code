package tech.lacambla.blog.examples.validation;

public class InvalidItemException extends RuntimeException {

  public InvalidItemException() {
  }

  public InvalidItemException(String message) {
    super(message);
  }

  public InvalidItemException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidItemException(Throwable cause) {
    super(cause);
  }

  public InvalidItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

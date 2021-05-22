package com.edge.expression.exception;

import lombok.Getter;

public class InvalidExpressionException extends RuntimeException {

  @Getter
  private final int offset;

  public InvalidExpressionException(String message, int offset) {
    super(message);
    this.offset = offset;
  }

}

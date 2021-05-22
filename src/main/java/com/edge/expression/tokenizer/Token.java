package com.edge.expression.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
  private TokenType type;
  private int start;
  private String data;

  @Override
  public String toString() {
    return type + "[" + data + "]";
  }
}

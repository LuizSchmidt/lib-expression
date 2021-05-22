package com.edge.expression.expression.value;

import java.util.Map;

import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class NumericValue implements Value {

  private Number value;

  public NumericValue(TokenStream stream) {
    Token token = stream.poll(TokenType.NUMERIC);
    if (token.getData() != null) {
      value = Double.parseDouble(token.getData());
    }
  }

  @Override
  public Object getValue(Map<String, Object> data) {
    return this.value;
  }

  @Override
  public String toString() {
    if (value == null) {
      return "null";
    } else if (value.doubleValue() % 1D == 0D) {
      return String.valueOf(value.longValue());
    } else {
      return value.toString();
    }
  }
}

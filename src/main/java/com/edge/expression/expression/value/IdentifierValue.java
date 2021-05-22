package com.edge.expression.expression.value;

import java.util.Map;

import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

import lombok.Getter;

public class IdentifierValue implements Value {

  @Getter
  private String identifier;

  public IdentifierValue(TokenStream stream) {
    Token token = stream.poll(TokenType.IDENTIFIER);
    identifier = token.getData();
  }

  @Override
  public Object getValue(Map<String, Object> data) {
    Object value = data.get(identifier);
    if (value == null) {
      return null;
    } else if (value instanceof Integer) {
      return ((Integer) value).doubleValue();
    } else if (value instanceof Long) {
      return ((Long) value).doubleValue();
    } else if (value instanceof Boolean) {
      return ((Boolean) value).booleanValue();
    } else {
      return value;
    }
  }

  @Override
  public String toString() {
    return this.identifier;
  }
}

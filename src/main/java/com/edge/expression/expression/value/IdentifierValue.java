package com.edge.expression.expression.value;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

import lombok.Data;
import lombok.Getter;

@Data
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
    } else if (value instanceof List) {
      return ((List<?>) value).stream().map(v -> v instanceof Number ? ((Number) v).doubleValue() : v)
          .collect(Collectors.toList());
    } else {
      return value;
    }
  }

  @Override
  public String toString() {
    return this.identifier;
  }
}

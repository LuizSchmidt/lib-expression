package com.edge.expression.expression.value;

import java.util.Map;

import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class LiteralValue implements Value {

  private String value;

  public LiteralValue(TokenStream stream) {
    Token token = stream.poll(TokenType.LITERAL);
    value = token.getData();
  }

  @Override
  public Object getValue(Map<String, Object> data) {
    return this.value;
  }

  @Override
  public String toString() {
    return "'" + value + "'";
  }
}

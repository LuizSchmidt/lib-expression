package com.edge.expression.expression.value;

import java.util.Locale;
import java.util.Map;

import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class BooleanValue implements Value {

  private boolean value;
  
  public BooleanValue(TokenStream stream) {
    Token token = stream.poll(TokenType.BOOLEAN);
    this.value = Boolean.valueOf(token.getData().toLowerCase(Locale.getDefault()));
  }
  
  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public Object getValue(Map<String, Object> data) {
    return value;
  }

}

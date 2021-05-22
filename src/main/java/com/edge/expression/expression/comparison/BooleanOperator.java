package com.edge.expression.expression.comparison;

import java.util.Locale;
import java.util.Map;

import com.edge.expression.expression.Operator;
import com.edge.expression.expression.value.Value;
import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class BooleanOperator implements Operator, Value {

  private boolean value;
  
  public BooleanOperator(TokenStream stream) {
    Token token = stream.poll(TokenType.BOOLEAN);
    this.value = Boolean.valueOf(token.getData().toLowerCase(Locale.getDefault()));
  }
  
  @Override
  public boolean evaluate(Map<String, Object> data) {
    return value;
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

package com.edge.expression.expression.comparison;

import java.util.Map;

import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class NotInOperator extends InOperator {

  public NotInOperator(TokenStream stream) {
    super(stream);
  }

  @Override
  protected TokenType getOperatorTokenType() {
    return TokenType.NOT_IN;
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    return !super.evaluate(data);
  }

  @Override
  public String toString() {
    return firstElement + " NOT IN " + secondElement;
  }
}

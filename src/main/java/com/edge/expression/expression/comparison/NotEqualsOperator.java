package com.edge.expression.expression.comparison;

import java.util.Map;

import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class NotEqualsOperator extends ComparisonOperator {

  public NotEqualsOperator(TokenStream stream) {
    super(stream);
  }

  @Override
  protected TokenType getOperatorTokenType() {
    return TokenType.NOT_EQUAL_TO;
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    Object firstValue = firstElement.getValue(data);
    Object secondValue = secondElement.getValue(data);

    if (firstValue == null || secondValue == null) {
      return (firstValue != secondValue);
    }
    return !firstValue.equals(secondValue);
  }

  @Override
  public String toString() {
    return firstElement + " != " + secondElement;
  }
}

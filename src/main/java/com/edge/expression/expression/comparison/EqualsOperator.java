package com.edge.expression.expression.comparison;

import java.util.Map;

import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class EqualsOperator extends ComparisonOperator {

  public EqualsOperator(TokenStream stream) {
    super(stream);
  }

  @Override
  protected TokenType getOperatorTokenType() {
    return TokenType.EQUAL_TO;
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    Object firstValue = firstElement.getValue(data);
    Object secondValue = secondElement.getValue(data);

    if (firstValue == null || secondValue == null) {
      return (firstValue == null && secondValue == null);
    }
    return firstValue.equals(secondValue);
  }

  @Override
  public String toString() {
    return firstElement + " = " + secondElement;
  }
}

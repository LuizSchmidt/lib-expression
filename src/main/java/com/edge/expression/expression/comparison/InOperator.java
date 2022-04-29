package com.edge.expression.expression.comparison;

import java.util.List;
import java.util.Map;

import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class InOperator extends ComparisonOperator {

  public InOperator(TokenStream stream) {
    super(stream);
  }

  @Override
  protected TokenType getOperatorTokenType() {
    return TokenType.IN;
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    Object firstValue = firstElement.getValue(data);
    Object secondValue = secondElement.getValue(data);

    if (firstValue == null || secondValue == null || !(secondValue instanceof List)) {
      return false;
    }

    return ((List<?>) secondValue).stream().anyMatch(v -> v.equals(firstValue));
  }

  @Override
  public String toString() {
    return firstElement + " IN " + secondElement;
  }
}

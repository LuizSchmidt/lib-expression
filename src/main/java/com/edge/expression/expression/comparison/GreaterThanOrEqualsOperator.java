package com.edge.expression.expression.comparison;

import java.util.Map;

import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class GreaterThanOrEqualsOperator extends ComparisonOperator {

  public GreaterThanOrEqualsOperator(TokenStream stream) {
    super(stream);
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    Object firstValue = firstElement.getValue(data);
    Object secondValue = secondElement.getValue(data);

    if (firstValue == null || secondValue == null) {
      return (firstValue == null && secondValue == null);
    } else if (!(firstValue instanceof Double) || !(secondValue instanceof Double)) {
      return false;
    }
    return (Double) firstValue >= (Double) secondValue;
  }

  @Override
  public String toString() {
    return firstElement + " >= " + secondElement;
  }

  @Override
  protected TokenType getOperatorTokenType() {
    return TokenType.GREATER_THAN_OR_EQUAL_TO;
  }

}

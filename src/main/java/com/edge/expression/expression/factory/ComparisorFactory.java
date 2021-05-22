package com.edge.expression.expression.factory;

import com.edge.expression.exception.InvalidExpressionException;
import com.edge.expression.expression.comparison.ComparisonOperator;
import com.edge.expression.expression.comparison.EqualsOperator;
import com.edge.expression.expression.comparison.GreaterThanOperator;
import com.edge.expression.expression.comparison.GreaterThanOrEqualsOperator;
import com.edge.expression.expression.comparison.LessThanOperator;
import com.edge.expression.expression.comparison.LessThanOrEqualsOperator;
import com.edge.expression.expression.comparison.NotEqualsOperator;
import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComparisorFactory {

  public static ComparisonOperator instanceOf(TokenStream stream) {
    Token token = stream.peek(1);
    switch (token.getType()) {
      case EQUAL_TO:
        return new EqualsOperator(stream);
      case NOT_EQUAL_TO:
        return new NotEqualsOperator(stream);
      case GREATER_THAN:
        return new GreaterThanOperator(stream);
      case GREATER_THAN_OR_EQUAL_TO:
        return new GreaterThanOrEqualsOperator(stream);
      case LESS_THAN:
        return new LessThanOperator(stream);
      case LESS_THAN_OR_EQUAL_TO:
        return new LessThanOrEqualsOperator(stream);
      default:
        throw new InvalidExpressionException("Unexpected token " + token, token.getStart());
    }
  }
}

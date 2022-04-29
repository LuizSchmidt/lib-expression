package com.edge.expression.expression.factory;

import com.edge.expression.exception.InvalidExpressionException;
import com.edge.expression.expression.comparison.ComparisonOperator;
import com.edge.expression.expression.comparison.EqualsOperator;
import com.edge.expression.expression.comparison.GreaterThanOperator;
import com.edge.expression.expression.comparison.GreaterThanOrEqualsOperator;
import com.edge.expression.expression.comparison.InOperator;
import com.edge.expression.expression.comparison.LessThanOperator;
import com.edge.expression.expression.comparison.LessThanOrEqualsOperator;
import com.edge.expression.expression.comparison.NotEqualsOperator;
import com.edge.expression.expression.comparison.NotInOperator;
import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComparisorFactory {

  public static ComparisonOperator instanceOf(TokenStream stream) {
    return instanceOf(stream, 1);
  }
  
  public static ComparisonOperator instanceOf(TokenStream stream, int offset) {
    Token token = stream.peek(offset);
    switch (token.getType()) {
      case NOT_EQUAL_TO:
        return new NotEqualsOperator(stream);
      case EQUAL_TO:
        return new EqualsOperator(stream);
      case GREATER_THAN:
        return new GreaterThanOperator(stream);
      case GREATER_THAN_OR_EQUAL_TO:
        return new GreaterThanOrEqualsOperator(stream);
      case LESS_THAN:
        return new LessThanOperator(stream);
      case LESS_THAN_OR_EQUAL_TO:
        return new LessThanOrEqualsOperator(stream);
      case NOT_IN:
        return new NotInOperator(stream);
      case IN:
        return new InOperator(stream);
      default:
        throw new InvalidExpressionException("Unexpected token " + token, token.getStart());
    }
  }
}

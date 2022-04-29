package com.edge.expression.expression.factory;

import com.edge.expression.exception.InvalidExpressionException;
import com.edge.expression.expression.value.ArrayValue;
import com.edge.expression.expression.value.BooleanValue;
import com.edge.expression.expression.value.IdentifierValue;
import com.edge.expression.expression.value.LiteralValue;
import com.edge.expression.expression.value.NumericValue;
import com.edge.expression.expression.value.Value;
import com.edge.expression.tokenizer.Token;
import com.edge.expression.tokenizer.TokenStream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueFactory {

  public static Value instanceOf(TokenStream stream) {
    Token token = stream.peek();
    switch (token.getType()) {
      case NUMERIC:
        return new NumericValue(stream);
      case LITERAL:
        return new LiteralValue(stream);
      case IDENTIFIER:
        return new IdentifierValue(stream);
      case BOOLEAN:
        return new BooleanValue(stream);
      case LEFT_BRACKET:
        return new ArrayValue(stream);
      default:
        throw new InvalidExpressionException("Unexpected token " + token, token.getStart());
    }
  }
}

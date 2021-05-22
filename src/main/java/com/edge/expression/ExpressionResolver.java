package com.edge.expression;

import java.util.Map;
import java.util.Objects;

import com.edge.expression.exception.InvalidExpressionException;
import com.edge.expression.expression.Expression;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.Tokenizer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpressionResolver {

  public static Expression compile(String expression) {
    Objects.requireNonNull(expression, "Expression cannot be null");
    if(expression.trim().length() == 0) {
      throw new InvalidExpressionException("Expression cannot be empty", 0);
    }
    
    TokenStream tokenStream = Tokenizer.tokenize(expression);
    return new Expression(tokenStream, true);
  }

  public static boolean evaluate(String expression, Map<String, Object> data) {
    return compile(expression).evaluate(data);
  }
}

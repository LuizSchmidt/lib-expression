package com.edge.expression.expression.logical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.edge.expression.expression.Expression;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class AndOperator implements LogicalOperator {
  
  protected final List<Expression> children = new ArrayList<>();

  public AndOperator(TokenStream stream) {
    do {
      children.add(new Expression(stream));
    } while (stream.isNextMoveForward(TokenType.AND));
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    return children.stream().allMatch(child -> child.evaluate(data));
  }

  @Override
  public String toString() {
    return children.stream().map(Object::toString).collect(Collectors.joining(" AND "));
  }
}

package com.edge.expression.expression.logical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.edge.expression.expression.Operator;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class OrOperator implements LogicalOperator {

  protected final List<Operator> children = new ArrayList<>();
  
  public OrOperator(TokenStream stream) {
    do {
      children.add(new AndOperator(stream));
    } while (stream.isNextMoveForward(TokenType.OR));
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    return children.stream().anyMatch(child -> child.evaluate(data));
  }

  @Override
  public String toString() {
    return children.stream().map(Object::toString).collect(Collectors.joining(" OR "));
  }
}

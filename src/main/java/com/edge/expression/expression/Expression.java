package com.edge.expression.expression;

import java.util.Map;

import com.edge.expression.expression.comparison.BooleanOperator;
import com.edge.expression.expression.factory.ComparisorFactory;
import com.edge.expression.expression.logical.OrOperator;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Expression implements Operator {

  private boolean root;
  private Operator operation;

  public Expression(TokenStream stream, boolean root) {
    this.root = root;
    create(stream);
  }

  public Expression(TokenStream stream) {
    create(stream);
  }

  private void create(TokenStream stream) {
    if (root) {
      operation = new OrOperator(stream);
    } else if (stream.isNext(TokenType.LEFT_PAR)) {
      stream.poll(TokenType.LEFT_PAR);
      operation = new OrOperator(stream);
      stream.poll(TokenType.RIGHT_PAR);
    } else if (stream.isNext(TokenType.BOOLEAN)) {
      operation = new BooleanOperator(stream);
    } else if (stream.isNext(TokenType.LEFT_BRACKET)) {
      int offset = stream.getOffSetNextToken(TokenType.RIGHT_BRACKET);
      operation = ComparisorFactory.instanceOf(stream, offset);
    } else {
      operation = ComparisorFactory.instanceOf(stream);
    }
  }

  @Override
  public String toString() {
    if (operation instanceof OrOperator && !root) {
      return "(" + operation + ")";
    } else {
      return operation.toString();
    }
  }

  @Override
  public boolean evaluate(Map<String, Object> data) {
    return operation.evaluate(data);
  }
}

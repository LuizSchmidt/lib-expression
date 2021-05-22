package com.edge.expression.expression.comparison;

import com.edge.expression.expression.Operator;
import com.edge.expression.expression.factory.ValueFactory;
import com.edge.expression.expression.value.Value;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public abstract class ComparisonOperator implements Operator {

  protected Value firstElement;
  protected Value secondElement;

  protected ComparisonOperator(TokenStream stream) {
    this.firstElement = ValueFactory.instanceOf(stream);
    stream.poll(getOperatorTokenType());
    this.secondElement = ValueFactory.instanceOf(stream);
  }

  protected abstract TokenType getOperatorTokenType();

}

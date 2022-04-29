package com.edge.expression.expression.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.edge.expression.expression.factory.ValueFactory;
import com.edge.expression.tokenizer.TokenStream;
import com.edge.expression.tokenizer.TokenType;

public class ArrayValue implements Value {

  private List<Value> values = new ArrayList<>();

  public ArrayValue(TokenStream stream) {
    stream.poll(TokenType.LEFT_BRACKET);
    
    while(!stream.isNext(TokenType.RIGHT_BRACKET)) {
      Value value = ValueFactory.instanceOf(stream);
      values.add(value);
      
      if(!stream.isNextMoveForward(TokenType.SEPARATOR)) {
        break;
      }
    }
    stream.poll(TokenType.RIGHT_BRACKET);
  }

  @Override
  public Object getValue(Map<String, Object> data) {
    return values.stream().map(v -> v.getValue(data)).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "'" + values + "'";
  }

}

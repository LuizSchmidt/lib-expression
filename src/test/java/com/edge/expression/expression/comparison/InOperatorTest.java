package com.edge.expression.expression.comparison;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.edge.expression.ExpressionResolver;

class InOperatorTest {

  Map<String, Object> info = Map.of("name", "foo", "age", 21, "departament", "TI", "fruits", List.of("apple", "banana", "pear"));
  
  @Test
  void testSingleIn() {
    validateTrue("name IN ['foo']");
    validateTrue("name IN ['foo', 'bar', 'blue', 2, 4, 1]");
    validateFalse("name IN ['bar', 2, 4]");
    validateTrue("age IN [21]");
    validateFalse("age IN [24]");
    validateTrue("age IN [age, 24, 21, departament]");
    validateTrue("21 IN [age, 24, 21, departament]");
    validateFalse("23 IN [age, 24, 21, departament]");
  }
  
  @Test
  void testInVariable() {
    validateTrue("'apple' IN fruits");
    validateFalse("'orange' IN fruits");
    validateFalse("name IN fruits");
    validateFalse("name IN 'foo'");
    validateFalse("name IN null");
    validateFalse("null IN null");
  }
  
  private void validateTrue(String expression) {
    assertTrue(ExpressionResolver.evaluate(expression, info), "equals TRUE");
  }

  private void validateFalse(String expression) {
    assertFalse(ExpressionResolver.evaluate(expression, info), "equals FALSE");
  }
}

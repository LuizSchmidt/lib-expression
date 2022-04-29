package com.edge.expression.expression.comparison;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.edge.expression.ExpressionResolver;

class NotInOperatorTest {

  Map<String, Object> info = Map.of("name", "foo", "age", 21, "departament", "TI", "isMarried", true);
  
  @Test
  void testSingleIn() {
    validateFalse("name NOT IN ['foo']");
    validateFalse("name NOT IN ['foo', 'bar', 'blue', 2, 4, 1]");
    validateTrue("name NOT IN ['bar', 2, 4]");
    validateFalse("age NOT IN [21]");
    validateTrue("age NOT IN [24]");
    validateFalse("age NOT IN [age, 24, 21, departament]");
    validateFalse("21 NOT IN [age, 24, 21, departament]");
    validateTrue("23 NOT IN [age, 24, 21, departament]");
  }
  
  @Test
  void testMultiIn() {
    validateFalse("name NOT IN ['foo']");
    validateFalse("name NOT IN ['foo', 'bar', 'blue', 2, 4, 1]");
    validateTrue("name NOT IN ['bar', 2, 4]");
    validateFalse("age NOT IN [21]");
    validateTrue("age NOT IN [24]");
    validateFalse("age NOT IN [age, 24, 21, departament]");
    validateFalse("21 NOT IN [age, 24, 21, departament]");
    validateTrue("23 NOT IN [age, 24, 21, departament]");
  }

  private void validateTrue(String expression) {
    assertTrue(ExpressionResolver.evaluate(expression, info), "equals TRUE");
  }

  private void validateFalse(String expression) {
    assertFalse(ExpressionResolver.evaluate(expression, info), "equals FALSE");
  }
}

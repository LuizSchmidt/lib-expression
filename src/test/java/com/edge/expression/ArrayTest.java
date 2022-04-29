package com.edge.expression;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ArrayValueTest {

  Map<String, Object> info = Map.of("name", List.of("foo", "bar"), "age", 21, "Departament", "TI", "isMarried", true, "numbers", List.of(1, 2, 3));

  @Test
  void testEquals() {
    validateTrue("name = ['foo', 'bar']");
    validateFalse("name = ['bar', 'foo']");
    validateTrue("['foo', 'bar'] = ['foo', 'bar']");
    validateFalse("['foo', 'bar'] = ['bar', 'foo']");
    
    validateTrue("numbers = [1, 2, 3]");
    validateFalse("numbers = [3, 2, 1]");
    validateTrue("[1, 2, 3] = [1, 2, 3]");
    validateFalse("[3, 2, 1] = [1, 2, 3]");
  }

  private void validateTrue(String expression) {
    assertTrue(ExpressionResolver.evaluate(expression, info), "equals TRUE");
  }

  private void validateFalse(String expression) {
    assertFalse(ExpressionResolver.evaluate(expression, info), "equals FALSE");
  }
}

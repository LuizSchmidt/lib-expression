package com.edge.expression;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ComplexExpressionEvaluateTest {

  Map<String, Object> info = Map.of("name", "foo", "age", 21, "Departament", "TI", "isMarried", true);

  @Test
  void testEquals() {
    validateTrue("name = 'foo' AND age >= 18 AND isMarried = true");
    validateFalse("name = 'foo' AND age >= 18 AND isMarried = false");
    validateTrue("name = 'foo' AND age >= 18 AND true");
    validateFalse("name = 'foo' AND age >= 18 AND false");
    
    validateTrue("name = 'foo' AND age >= 18 OR isMarried = false");
    validateTrue("name = 'foo' AND (age >= 18 OR isMarried = false)");
    validateTrue("(name = 'foo' AND age >= 18) OR isMarried = false");
    validateFalse("(name = 'bar' AND age >= 18) OR isMarried = false");
    validateTrue("(name = 'bar' OR FALSE OR age = 18) OR isMarried = true");
    validateFalse("name = 'bar' OR age = 10 OR isMarried = false AND name IN ['bar', 2, 4]");
  }

  private void validateTrue(String expression) {
    assertTrue(ExpressionResolver.evaluate(expression, info), "equals TRUE");
  }

  private void validateFalse(String expression) {
    assertFalse(ExpressionResolver.evaluate(expression, info), "equals FALSE");
  }
}

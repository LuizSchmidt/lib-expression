package com.edge.expression;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

class SimpleExpressionEvaluateTest {

  Map<String, Object> info = Map.of("A", 1);

  @Test
  void testEquals() {
    assertTrue(ExpressionResolver.evaluate("A = 1", info), "equals TRUE");
    assertFalse(ExpressionResolver.evaluate("A = 2", info), "equals FALSE");
  }

  @Test
  void testNotEquals() {
    assertTrue(ExpressionResolver.evaluate("A != 2", info), "not equals TRUE");
    assertFalse(ExpressionResolver.evaluate("A != 1", info), "not equals FALSE");
  }

  @Test
  void testGreaterThan() {
    assertTrue(ExpressionResolver.evaluate("A > 0", info), "grater than TRUE");
    assertFalse(ExpressionResolver.evaluate("A > 1", info), "greate than FALSE");
  }

  @Test
  void testGreaterThanOrEquals() {
    assertTrue(ExpressionResolver.evaluate("A >= 1", info), "greaten than or equals to TRUE");
    assertTrue(ExpressionResolver.evaluate("A >= 0", info), "greaten than or equals to TRUE");
    assertFalse(ExpressionResolver.evaluate("A >= 2", info), "greaten than or equals to FALSE");
  }
  
  @Test
  void testLessThan() {
    assertTrue(ExpressionResolver.evaluate("A < 2", info), "less than TRUE");
    assertFalse(ExpressionResolver.evaluate("A < 1", info), "less than FALSE");
  }

  @Test
  void testLessThanOrEquals() {
    assertTrue(ExpressionResolver.evaluate("A <= 1", info), "less than or equals to TRUE");
    assertTrue(ExpressionResolver.evaluate("A <= 2", info), "less than or equals to TRUE");
    assertFalse(ExpressionResolver.evaluate("A <= 0", info), "less than or equals to FALSE");
  }

  @Test
  void testBoolean() {
    assertTrue(ExpressionResolver.evaluate("true", info), "boolean equals TRUE");
    assertTrue(ExpressionResolver.evaluate("True", info), "boolean equals TRUE");
    assertTrue(ExpressionResolver.evaluate("TRUE", info), "boolean equals TRUE");
    assertTrue(ExpressionResolver.evaluate("trUe", info), "boolean equals TRUE");
    assertFalse(ExpressionResolver.evaluate("false", info), "boolean equals FALSE");
    assertFalse(ExpressionResolver.evaluate("False", info), "boolean equals FALSE");
    assertFalse(ExpressionResolver.evaluate("FALSE", info), "boolean equals FALSE");
    assertFalse(ExpressionResolver.evaluate("faLse", info), "boolean equals FALSE");
  }
  
}

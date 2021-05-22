package com.edge.expression;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ObjectExpressionEvaluateTest {

  Map<String, Object> info = Map.of("customer.name", "luiz", "customer.age", 21, "value_payment", 10.5, "payment.method", "card", "payment.number", "100932122");

  @Test
  void testEquals() {
    assertTrue(ExpressionResolver.evaluate("customer.name = 'luiz'", info), "equals TRUE");
    assertTrue(ExpressionResolver.evaluate("customer.age = 21", info), "equals TRUE");
    
    assertTrue(ExpressionResolver.evaluate("value_payment >= 10.1", info), "equals TRUE");
  }
}

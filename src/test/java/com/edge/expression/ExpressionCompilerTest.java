package com.edge.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.edge.expression.exception.InvalidExpressionException;
import com.edge.expression.expression.Expression;
import com.edge.expression.tokenizer.Tokenizer;

class ExpressionCompilerTest {

  @BeforeAll
  static void beforeAll() {
    System.out.println("REGEXR: " + Tokenizer.TOKENS.pattern());
  }

  @Test
  void testSimpleCompile() {
    assertEquals("A = 1", ExpressionResolver.compile("A = 1").toString(), "simple");
    assertEquals("(A = 1)", ExpressionResolver.compile("(A = 1)").toString(), "With Parentheses");
    assertEquals("A = 1", ExpressionResolver.compile("A=1").toString(), "with no spaces");
    assertEquals("A = 1", ExpressionResolver.compile("   A    =    1    ").toString(), "with a lot of spaces");
  }

  @Test
  void testMultiplesCompile() {
    Expression expression = ExpressionResolver.compile("A = 1 AND B = 2");
    assertEquals("A = 1 AND B = 2", expression.toString(), "must be equals with parentheses");
    assertEquals("A = 1 AND (B = 2 OR C = 3)", ExpressionResolver.compile("A = 1 AND (B = 2 OR C = 3)").toString(),
        "multiple with parentheses");
  }

  @Test
  void testComplexExpression() {
    Expression expression = ExpressionResolver.compile("(A = 1 OR B=2 AND c=1) AND (((B = 2)) OR C = 3)");
    assertEquals("(A = 1 OR B = 2 AND c = 1) AND (((B = 2)) OR C = 3)", expression.toString(),
        "must be equals with parentheses");
  }

  @Test
  void testInvalidExpressions() {
    assertThrows(InvalidExpressionException.class, () -> ExpressionResolver.compile("A ( = B"),
        "invalid position of token");
    assertThrows(InvalidExpressionException.class, () -> ExpressionResolver.compile("A * = B"), "invalid token");
  }

  @Test
  void testAllTypesOfComparisor() {
    assertEquals("A = 1", ExpressionResolver.compile("A = 1").toString(), "equals (=)");
    assertEquals("A != 1", ExpressionResolver.compile("A != 1").toString(), "not equals (!=)");
    assertEquals("'1' = A", ExpressionResolver.compile("'1' = A").toString(), "equals (=)");
    assertEquals("'1' != A", ExpressionResolver.compile("'1' != A").toString(), "not equals (!=)");
    assertEquals("A > 1", ExpressionResolver.compile("A > 1").toString(), "greater than (>)");
    assertEquals("A >= 1", ExpressionResolver.compile("A >= 1").toString(), "greater than and equals (>=)");
    assertEquals("A < 1", ExpressionResolver.compile("A < 1").toString(), "less than (<)");
    assertEquals("A <= 1", ExpressionResolver.compile("A <= 1").toString(), "less than and equals (<=)");
    assertEquals("A IN 1", ExpressionResolver.compile("A IN 1").toString(), "contains (IN)");
    assertEquals("A NOT IN 1", ExpressionResolver.compile("A NOT IN 1").toString(), "not contains (NOT IN)");
  }
  
  @Test
  void testBooleanTrueType() {
    assertEquals("true", ExpressionResolver.compile("true").toString(), "boolean (true)");
    assertEquals("true", ExpressionResolver.compile("TRUE").toString(), "boolean (TRUE)");

    assertEquals("true", ExpressionResolver.compile("True").toString(), "boolean (True)");
    assertEquals("true", ExpressionResolver.compile("TrUe").toString(), "boolean (TrUe)");
  }
  
  @Test
  void testBooleanFalseType() {
    assertEquals("false", ExpressionResolver.compile("false").toString(), "boolean (false)");
    assertEquals("false", ExpressionResolver.compile("FALSE").toString(), "boolean (TrFALSE)");

    assertEquals("false", ExpressionResolver.compile("False").toString(), "boolean (False)");
    assertEquals("false", ExpressionResolver.compile("FaLsE").toString(), "boolean (FaLsE)");
  }

}

package com.edge.expression.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TokenType {
  
  /**
   * Logical Operators
   */
  AND("(AND)"),
  OR("(OR)"),
  
  /**
   * Array
   */
  LEFT_BRACKET("(\\[)"),
  RIGHT_BRACKET("(\\])"),
  SEPARATOR("(\\,)"),
  
  /**
   * Parentheses
   */
  LEFT_PAR("(\\()"),
  RIGHT_PAR("(\\))"),
  
  /**
   * Comparison Operators
   */
  EQUAL_TO("([^><!]{0}=)"),
  NOT_EQUAL_TO("(!=)"),
  GREATER_THAN("(>[^=])"),
  GREATER_THAN_OR_EQUAL_TO("(>=)"),
  LESS_THAN("(<[^=])"),
  LESS_THAN_OR_EQUAL_TO("(<=)"),
  NOT_IN ("(NOT IN)"),
  IN("(IN)"),
  
  /**
   * Identificators
   */
  BOOLEAN("(TRUE|FALSE|true|false)"),
  LITERAL("\\'([^\\']+)\\'"),
  NUMERIC("([0-9]+)"),
  IDENTIFIER("([.\\w]+)"),
  
  /**
   * Ignores
   */
  WHITESPACE("(\\s+)"),
  EOF("()"), 
  ;
  
  @Getter
  private String regex;
}

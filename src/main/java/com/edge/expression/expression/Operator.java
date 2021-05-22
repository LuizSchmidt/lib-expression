package com.edge.expression.expression;

import java.util.Map;

public interface Operator {
  
  /**
   * Evaluate a expression with data Map<String, Object>
   * @param data
   * @return true|false
   */
  boolean evaluate(Map<String, Object> data);
  
}

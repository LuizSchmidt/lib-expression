package com.edge.expression.expression.value;

import java.util.Map;

public interface Value {
  
  Object getValue(Map<String, Object> data);
}

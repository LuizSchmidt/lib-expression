package com.edge.expression.tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.edge.expression.exception.InvalidExpressionException;

class TokenStreamTest {

  @Test
  void testSimpleTokenizer() {
    TokenStream tokenStream = Tokenizer.tokenize("FOO = 'BAR'");
    validToken(tokenStream.poll(TokenType.IDENTIFIER), "FOO", 0);
    validToken(tokenStream.poll(TokenType.EQUAL_TO), "=", 4);
    validToken(tokenStream.poll(TokenType.LITERAL), "BAR", 6);
    validToken(tokenStream.poll(TokenType.EOF), null, 11);
    assertNull(tokenStream.peek(), "Must be null because is end.");
    
    assertEquals("[IDENTIFIER[FOO], EQUAL_TO[=], LITERAL[BAR], EOF[null]]", tokenStream.toString(), "toString");
  }

  @Test
  void testExceptionsTokenizer() {
    TokenStream tokenStream = Tokenizer.tokenize("A = '0'");
    assertThrows(InvalidExpressionException.class, () -> tokenStream.poll(TokenType.AND),
        "AND is not a first element");
    
    assertThrows(InvalidExpressionException.class, () -> tokenStream.poll(TokenType.AND, TokenType.LITERAL),
        "AND or LITERAL is not a first element");
  }

  private void validToken(Token token, String value, int index) {
    assertNotNull(token, "Token must exists");
    assertEquals(value, token.getData(), "match value");
    assertEquals(index, token.getStart(), "match index");
  }

}

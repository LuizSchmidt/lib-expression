package com.edge.expression.tokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.edge.expression.exception.InvalidExpressionException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenStream {

  @NonNull
  private final List<Token> tokens;
  private AtomicInteger offset = new AtomicInteger();

//  /**
//   * Verify type of next Element
//   * 
//   * @param number: define number offset to go
//   * @return
//   * @throws ParseException
//   */
//  public boolean isNextElementTypeOf(TokenType type, int number) {
//    if (tokens.size() > offset.get() + number) {
//      return tokens.get(offset.get() + number).getType() == type;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Verify type of next element
//   * 
//   * @return
//   * @throws ParseException
//   */
//  public Token verifyNextElement() {
//    return this.verifyNextElement(0);
//  }
//
//  /**
//   * Verify type of next element
//   * 
//   * @param number: define number offset to go
//   * @return
//   * @throws ParseException
//   */
//  public Token verifyNextElement(int number) {
//    if (tokens.size() > offset.get() + number) {
//      return tokens.get(offset.get() + number);
//    } else {
//      return null;
//    }
//  }
//
//  /**
//   * Consume next token of one of given type
//   * 
//   * @param type
//   * @return
//   * @throws ParseException
//   */
//  public Token consume(TokenType... types) {
//    Token token = tokens.get(offset.getAndIncrement());
//    if (Arrays.asList(types).stream().noneMatch(type -> token.getType() == type)) {
//      throw new InvalidExpressionException(
//          "Unexpected token at " + token.getStart() + ": " + token + ", was looking for one of " + types + ".",
//          token.getStart());
//    }
//    return token;
//  }
//
//  /**
//   * Consume next token of given type
//   * 
//   * @param type
//   * @return
//   * @throws ParseException
//   */
//  public Token consume(TokenType type) {
//    Token token = tokens.get(offset.getAndIncrement());
//    if (token.getType() != type) {
//      throw new InvalidExpressionException(
//          "Unexpected token at " + token.getStart() + ": " + token + ", was looking for " + type + ".",
//          token.getStart());
//    }
//    return token;
//  }
//
//  /**
//   * Verify if next element is of Type Consume token of given type Return null and
//   * don't advance if type differs
//   * 
//   * @param type
//   * @return
//   */
//  public Token consumeIfNext(TokenType type) {
//    Token token = tokens.get(offset.get());
//    if (token.getType() == type) {
//      offset.incrementAndGet();
//      return token;
//    }
//    return null;
//  }
//
//  
//  
//  
  
  
  public boolean isNextMoveForward(TokenType... types) {
    if(isNext(types)) {
      this.poll();
      return true;
    }else {
      return false;
    }
  }
  
  
  /**
   * Same as {@link #peek(TokenType...)} but return types is not null
   * @param types
   * @return true if token is not null
   */
  public boolean isNext(TokenType... types) {
    return this.peek(types) != null;
  }

  /**
   * Same as {@link #peek(int)} with offset of 0
   *
   * @return the Token
   */
  public Token peek() {
    return this.peek(0);
  }

  /**
   * Same as {@link #peek(int, TokenType...) with offset of 0
   * 
   * @param types
   * @return the Token
   */
  public Token peek(TokenType... types) {
    return this.peek(0, types);
  }

  /**
   * Same as {@link #peek(int), but the token must be one of Type
   * 
   * @param offset
   * @param types
   * @return
   */
  public Token peek(int offset, TokenType... types) {
    Token token = this.peek(offset);
    if (Arrays.asList(types).stream().noneMatch(type -> token.getType() == type)) {
      return null;
    } else {
      return token;
    }
  }

  /**
   * Retrieves, but does not move forward, the head Token of this stream, or
   * returns {@code null} if this stream is empty.
   *
   * @param offset position
   * @return the head token of this stream, or {@code null} if this stream is
   *         empty
   */
  public Token peek(int offset) {
    if (tokens.size() > this.offset.get() + offset) {
      return tokens.get(this.offset.get() + offset);
    } else {
      return null;
    }
  }

  /**
   * Same as {@link #poll()} but the token must be one of Type
   * 
   * @param types
   * @return the Token
   */
  public Token poll(TokenType... types) {
    Token token = this.poll();
    if (Arrays.asList(types).stream().noneMatch(type -> token.getType() == type)) {
      throw new InvalidExpressionException(
          "Unexpected token at " + token.getStart() + ": " + token + ", was looking for one of " + Arrays.asList(types) + ".",
          token.getStart());
    } else {
      return token;
    }
  }

  /**
   * Retrieves the head token and move forward the offset of this stream, or
   * returns {@code null} if this stream is empty.
   *
   * @return the head token of this stream, or {@code null} if this stream is
   *         empty
   */
  public Token poll() {
    if (tokens.size() > offset.get()) {
      return tokens.get(offset.getAndIncrement());
    } else {
      return null;
    }
  }

  @Override
  public String toString() {
    return tokens.toString();
  }


  public int getOffSetNextToken(TokenType tokenType) {
    int count = 0; 
    Token token = null;
    
    while(token == null) {
      token = peek(count, tokenType);
      count++;
    }
    
    return count;
  }
}
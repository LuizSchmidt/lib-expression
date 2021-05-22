package com.edge.expression.tokenizer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.edge.expression.exception.InvalidExpressionException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tokenizer {

  public static final Pattern TOKENS;

  static {
    StringBuilder regex = new StringBuilder();
    Arrays.asList(TokenType.values()).stream().map(token -> "|".concat(token.getRegex())).forEach(regex::append);
    TOKENS = Pattern.compile(regex.substring(1), Pattern.CASE_INSENSITIVE);
  }

  /**
   * Create a TokenStream from a expression
   * 
   * @param expression
   * @return
   * @throws ParseException
   */
  public static TokenStream tokenize(String expression) {
    Matcher matcher = TOKENS.matcher(expression);
    List<Token> tokens = new ArrayList<>();

    AtomicInteger offset = new AtomicInteger();
    while (offset.get() != expression.length()) {
      if (!matcher.find() || matcher.start() != offset.get()) {
        throw new InvalidExpressionException(
            "Unexpected token at index " + (offset.get()) + ": [" + expression.charAt(offset.get()) + "]",
            offset.get());
      }

      tokens.addAll(IntStream.range(0, TokenType.values().length).filter(i -> matcher.group(i + 1) != null)
          .mapToObj(i -> new Token(TokenType.values()[i], offset.get(), matcher.group(i + 1)))
          .filter(f -> f.getType() != TokenType.WHITESPACE).collect(Collectors.toList()));

      offset.set(matcher.end());
    }
    tokens.add(new Token(TokenType.EOF, offset.get(), null));
    return new TokenStream(tokens);
  }
}

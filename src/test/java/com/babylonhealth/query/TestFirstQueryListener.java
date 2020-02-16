package com.babylonhealth.query;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

import com.babylonhealth.antlr.FieldLexer;
import com.babylonhealth.antlr.QueryLexer;
import com.babylonhealth.antlr.QueryParser;

public class TestFirstQueryListener {

  @Test
  public void validateResult() {
    QueryLexer lexer = new QueryLexer(CharStreams.fromString("1 > 2"));
    QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
    parser.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
      }
    });

    parser.addParseListener(new FirstQueryListener<>());
    QueryParser.ParseContext parse = parser.parse();
    System.out.println(parse);
  }
}

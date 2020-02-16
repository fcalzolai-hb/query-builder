package com.babylonhealth.query;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.Test;

import com.babylonhealth.antlr.QueryLexer;
import com.babylonhealth.antlr.QueryParser;

import static org.junit.Assert.fail;

public class SyntaxTest {

  @Test
  public void dateLexicalAnalizer() {
    Pair<String, Boolean>[] expressions = new Pair[] {
        new Pair<>("2020-02-01", true),
//        new Pair<>("2019-06-10T13:15:00Z", true),
//        new Pair<>("2019-06-10T10:15:00+02:00", true),
    };

    for (Pair<String, Boolean> pair : expressions) {
      validateExpressionSyntax(pair);
    }
  }

  @Test
  public void validateParser() {
    Pair<String, Boolean>[] expressions = new Pair[] {
        new Pair<>("1 > 2", true),
        new Pair<>("1 >>= 1.0", false),
        new Pair<>("TRUE", true),
        new Pair<>("FALSE", true),
        new Pair<>("TRUE OR FALSE", true),
        new Pair<>("TRUE OR FALSE AND TRUE", true),
        new Pair<>("TRUE AND FALSE", true)
    };

    for (Pair<String, Boolean> pair : expressions) {
      validateExpressionSyntax(pair);
    }
  }

  private void validateExpressionSyntax(Pair<String, Boolean> pair) {
    QueryLexer lexer = new QueryLexer(new ANTLRInputStream(pair.a));
    QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
    parser.addErrorListener(new QueryErrorListener());
    try {
      QueryParser.ParseContext parse = parser.parse();
      if (!pair.b) {
        fail("Unexpected success");
      }
    } catch (Exception e) {
      if (pair.b) {
        fail("Unexpected failure");
      }
    }
  }
}

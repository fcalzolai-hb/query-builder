package com.babylonhealth.query;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

import com.babylonhealth.antlr.QueryLexer;
import com.babylonhealth.antlr.QueryParser;

public class QueryTest {

  @Test
  public void test() {
    Map<String, Object> variables = new HashMap<>() {{
      put("A", true);
      put("a", true);
      put("B", false);
      put("b", false);
      put("C", 42.0);
      put("c", 42.0);
      put("D", -999.0);
      put("d", -1999.0);
      put("E", 42.001);
      put("e", 142.001);
      put("F", 42.001);
      put("f", 42.001);
      put("G", -1.0);
      put("g", -1.0);
    }};

    String[] expressions = {
        "1 > 2",
        "1 >= 1.0",
        "TRUE = FALSE",
        "FALSE = FALSE",
        "A OR B",
        "B",
        "A = B",
        "c = C",
        "E > D",
        "B OR (c = B OR (A = A AND c = C AND E > D))",
        "(A = a OR B = b OR C = c AND ((D = d AND E = e) OR (F = f AND G = g)))"
    };

    Boolean[] results = {false, true, false, true, true, false, false, true, true, true, true};

    for (int i = 0; i < expressions.length; i++) {
      String expression = expressions[i];
      QueryLexer lexer = new QueryLexer(new ANTLRInputStream(expression));
      QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
      Object result = new EvalVisitor(variables).visit(parser.parse());
      System.out.printf("%-70s -> %s\n", expression, result);
      Assert.assertEquals(results[i], result);
    }
  }
}

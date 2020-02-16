package com.babylonhealth.query;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.Assert;
import org.junit.Test;

import com.babylonhealth.antlr.QueryLexer;
import com.babylonhealth.antlr.QueryParser;

public class QueryTest {

  @Test
  public void validateResult() {
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

    Pair<String, Boolean>[] expressions = new Pair[] {
        new Pair<>("NOT FALSE AND TRUE", true),
        new Pair<>("1 > 2", false),
        new Pair<>("1 >: 1.0", true),
        new Pair<>("FALSE : FALSE", true),
        new Pair<>("A OR B", true),
        new Pair<>("B", false),
        new Pair<>("A : B", false),
        new Pair<>("c : C", true),
        new Pair<>("E > D", true),
        new Pair<>("B OR (c : B OR (A : A AND c : C AND E > D))", true),
        new Pair<>("(A : a OR B : b OR C : c AND ((D : d AND E : e) OR (F : f AND G : g)))", true)
    };

    for (Pair<String, Boolean> pair : expressions) {
      QueryLexer lexer = new QueryLexer(new ANTLRInputStream(pair.a));
      QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
      Object result = new EvalVisitor(variables).visit(parser.parse());
      Assert.assertEquals(pair.b, result);
//      System.out.printf("%-70s -> %s\n", pair.a, result);
    }
  }
}

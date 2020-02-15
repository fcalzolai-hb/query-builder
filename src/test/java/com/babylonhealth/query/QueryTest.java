package com.babylonhealth.query;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import com.babylonhealth.antlr.QueryBaseVisitor;
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

    for (String expression : expressions) {
      QueryLexer lexer = new QueryLexer(new ANTLRInputStream(expression));
      QueryParser parser = new QueryParser(new CommonTokenStream(lexer));
      Object result = new EvalVisitor(variables).visit(parser.parse());
      System.out.printf("%-70s -> %s\n", expression, result);
    }
  }
}

class EvalVisitor extends QueryBaseVisitor<Object> {

  private final Map<String, Object> variables;

  public EvalVisitor(Map<String, Object> variables) {
    this.variables = variables;
  }

  @Override
  public Object visitParse(QueryParser.ParseContext ctx) {
    return super.visit(ctx.expression());
  }

  @Override
  public Object visitDecimalExpression(QueryParser.DecimalExpressionContext ctx) {
    return Double.valueOf(ctx.DECIMAL().getText());
  }

  @Override
  public Object visitIdentifierExpression(QueryParser.IdentifierExpressionContext ctx) {
    return variables.get(ctx.IDENTIFIER().getText());
  }

  @Override
  public Object visitNotExpression(QueryParser.NotExpressionContext ctx) {
    return !((Boolean)this.visit(ctx.expression()));
  }

  @Override
  public Object visitParenExpression(QueryParser.ParenExpressionContext ctx) {
    return super.visit(ctx.expression());
  }

  @Override
  public Object visitComparatorExpression(QueryParser.ComparatorExpressionContext ctx) {
    if (ctx.op.EQ() != null) {
      return this.visit(ctx.left).equals(this.visit(ctx.right));
    }
    else if (ctx.op.LE() != null) {
      return asDouble(ctx.left) <= asDouble(ctx.right);
    }
    else if (ctx.op.GE() != null) {
      return asDouble(ctx.left) >= asDouble(ctx.right);
    }
    else if (ctx.op.LT() != null) {
      return asDouble(ctx.left) < asDouble(ctx.right);
    }
    else if (ctx.op.GT() != null) {
      return asDouble(ctx.left) > asDouble(ctx.right);
    }
    throw new RuntimeException("not implemented: comparator operator " + ctx.op.getText());
  }

  @Override
  public Object visitBinaryExpression(QueryParser.BinaryExpressionContext ctx) {
    if (ctx.op.AND() != null) {
      return asBoolean(ctx.left) && asBoolean(ctx.right);
    }
    else if (ctx.op.OR() != null) {
      return asBoolean(ctx.left) || asBoolean(ctx.right);
    }
    throw new RuntimeException("not implemented: binary operator " + ctx.op.getText());
  }

  @Override
  public Object visitBoolExpression(QueryParser.BoolExpressionContext ctx) {
    return Boolean.valueOf(ctx.getText());
  }

  private boolean asBoolean(QueryParser.ExpressionContext ctx) {
    return (boolean)visit(ctx);
  }

  private double asDouble(QueryParser.ExpressionContext ctx) {
    return (double)visit(ctx);
  }
}
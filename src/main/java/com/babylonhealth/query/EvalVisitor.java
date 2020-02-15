package com.babylonhealth.query;

import java.util.Map;

import com.babylonhealth.antlr.QueryBaseVisitor;
import com.babylonhealth.antlr.QueryParser;

public
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

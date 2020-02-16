package com.babylonhealth.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.babylonhealth.antlr.QueryBaseListener;
import com.babylonhealth.antlr.QueryBaseVisitor;
import com.babylonhealth.antlr.QueryParser;

public class FirstQueryListener<T> extends QueryBaseListener {

  private Root<T> root;
  private CriteriaQuery<?> query;
  private CriteriaBuilder builder;

  @Override
  public void enterParse(QueryParser.ParseContext ctx) {
    super.enterParse(ctx);
  }

  @Override
  public void exitParse(QueryParser.ParseContext ctx) {
    super.exitParse(ctx);
  }
}

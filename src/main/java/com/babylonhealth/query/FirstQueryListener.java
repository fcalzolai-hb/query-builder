package com.babylonhealth.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.babylonhealth.antlr.QueryBaseListener;
import com.babylonhealth.antlr.QueryParser;

@Slf4j
@Getter
public class FirstQueryListener<T> extends QueryBaseListener {

  private Root<T> root;
  private CriteriaQuery<?> query;
  private CriteriaBuilder builder;

  private Boolean booleanExp;
  private Predicate predicate;

  public FirstQueryListener(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    this.root = root;
    this.query = query;
    this.builder = builder;
  }

  @Override
  public void enterParse(QueryParser.ParseContext ctx) {
    super.enterParse(ctx);
  }

  @Override
  public void exitParse(QueryParser.ParseContext ctx) {
    super.exitParse(ctx);
  }

  @Override
  public void exitBoolExpression(QueryParser.BoolExpressionContext ctx) {
    booleanExp = Boolean.valueOf(ctx.getText());
  }

  @Override
  public void exitComparatorExpression(QueryParser.ComparatorExpressionContext ctx) {
    String key = ctx.left.getText();
    Path path;
    try {
      path = root.<String>get(key);
    } catch (IllegalArgumentException e) {
      log.warn("Search field does not exist search_key:{}", key);
      throw e;
    }

    //TODO create separate builders:
    //  if (path.getJavaType().equals(OffsetDateTime.class))
    // path.getJavaType() contains the type of the element for the query
    // Use path.getJavaType() to define operation


    String value = ctx.right.getText();
    predicate = builder.like(builder.upper(path).as(String.class), "%" + value + "%");
  }

  //TODO override more methodon order to create more complex predicate
}

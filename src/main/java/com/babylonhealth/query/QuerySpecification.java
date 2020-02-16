package com.babylonhealth.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.data.jpa.domain.Specification;

import com.babylonhealth.antlr.QueryLexer;
import com.babylonhealth.antlr.QueryParser;

public class QuerySpecification<T> implements Specification<T> {

  private QueryParser parser;

  public QuerySpecification(String criteria) {
    QueryLexer lexer = new QueryLexer(CharStreams.fromString(criteria));
    parser = new QueryParser(new CommonTokenStream(lexer));
    parser.addErrorListener(new QueryErrorListener());
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    FirstQueryListener<T> listener = new FirstQueryListener<>(root, criteriaQuery, criteriaBuilder);
    parser.addParseListener(listener);
    QueryParser.ParseContext parse = parser.parse();
    System.out.println(parse);
    return listener.getPredicate();
  }
}

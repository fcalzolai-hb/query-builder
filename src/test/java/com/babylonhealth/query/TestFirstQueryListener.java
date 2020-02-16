package com.babylonhealth.query;

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

public class TestFirstQueryListener {

  @Test
  public void validateResult() {
    //a user searches for partners with query
    //    lexer = new QueryLexer(CharStreams.fromString("display_name:Bupa OR display_name:NHS"));

    Specification<String> s = new QuerySpecification<>("display_name:Bupa");
    //TODO create an in memory DB to test the QuerySpecification
//    s.toPredicate(new CriteriaBuilderImpl(), )
    System.out.println(s);
  }
}

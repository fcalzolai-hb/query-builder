package com.babylonhealth.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = "/schema.sql")
@Sql(scripts = "/data.sql")
public class LoadFromSqlIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Test
  public void givenALoadFromScript_thenReturnEmployee() {
    // when
    String name = "Bob";
    Employee found = employeeRepository.findByName(name);

    // then
    assertEquals(name, found.getName());
  }
}


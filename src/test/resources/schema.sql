drop table if exists person;

create table person(
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  PRIMARY KEY ( ID )
);
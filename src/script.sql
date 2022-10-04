create database lw_psp;

use lw_psp;

insert into country(name, square, population, capitalName, capitalSquare, capitalPopulation)
values ('name1', 0, 0, 'cname1', 0, 0),
       ('name2', 0, 0, 'cname2', 0, 0),
       ('name3', 0, 0, 'cname3', 0, 0),
       ('name4', 0, 0, 'cname4', 0, 0),
       ('name5', 0, 0, 'cname5', 0, 0);

CREATE TABLE country
(
    id                int auto_increment primary key,
    name              varchar(50),
    square            double,
    population        int,
    capitalName       varchar(50),
    capitalSquare     double,
    capitalPopulation int
);
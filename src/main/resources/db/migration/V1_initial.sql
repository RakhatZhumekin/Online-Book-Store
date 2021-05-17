create table authors (
    id serial,
    name varchar(50),
    birthday date
)

create table sections (
    id serial,
    name varchar(50)
)

create table books (
    id serial,
    name varchar(50),
    author_id int,
    section_id int,
    language varchar(20),
    price int
)
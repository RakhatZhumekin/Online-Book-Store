create table role (
    id serial primary key,
    name varchar(50) unique
)

create table users (
    id bigserial primary key,
    name varchar(50) unique,
    role_id int
)
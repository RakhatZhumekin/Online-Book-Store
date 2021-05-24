update users
set password = 'password'
where id = 1;

alter table users
alter column password
set not null
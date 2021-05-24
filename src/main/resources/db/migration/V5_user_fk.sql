alter table users
add constraint fk_users_role
foreign key (role_id) references role(id)
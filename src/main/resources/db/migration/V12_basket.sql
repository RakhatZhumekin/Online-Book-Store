create table basket_item (
    id bigserial primary key,
    user_id bigint,
    book_id int,
    quantity int
);

alter table basket_item
add constraint fk_user
foreign key (user_id) references users(id);

alter table basket_item
add constraint fk_book
foreign key (book_id) references books(id);
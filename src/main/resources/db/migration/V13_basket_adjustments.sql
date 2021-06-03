alter table books
add constraint pk_books primary key (id);

alter table basket_item
add constraint fk_book
foreign key (book_id) references books(id);
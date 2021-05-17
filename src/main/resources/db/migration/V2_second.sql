alter table authors
add constraint pk_authors primary key (id)

alter table sections
add constraint pk_sections primary key (id)

alter table books
add constraint fk_books_author
foreign key (author_id) references authors(id)

alter table books
add constraint fk_books_section
foreign key (section_id) references sections(id)
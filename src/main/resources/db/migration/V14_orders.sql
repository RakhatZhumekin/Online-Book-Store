create table orders (
    id bigserial primary key,
    user_id bigint,
    basket_item_ids int[]
);

alter table orders
add constraint fk_user
foreign key (user_id) references users(id);

alter table basket_item
add column active bool default true;
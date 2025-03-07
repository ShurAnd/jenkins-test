create table if not exist magic_core(
    id serial primary key,
    quantity bigint not null,
    owner varchar(100) not null
);
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create table if not exists user_role (
    id serial  primary key,
    title varchar unique
);

create table if not exists user_status (
    id serial primary key ,
    title varchar unique
);

create table if not exists system_user (
    id serial primary key,
    role int not null ,
    status int not null ,
    name varchar not null ,

    constraint fk_role_title
        FOREIGN KEY (role)
            REFERENCES user_role(id),
    constraint fk_status_title
        FOREIGN KEY (status)
            REFERENCES user_status(id)
);

create table if not exists order_status (
    id serial primary key ,
    title varchar unique
);

create table if not exists order_list(
    id serial primary key ,
    user_id int not null ,
    status int not null ,
    constraint fk_order_user_id
        foreign key (user_id)
            references system_user(id),
    constraint fk_order_status
        foreign key (status)
            references order_status(id)
);

create table if not exists kitchen_worker_status(
    id serial primary key ,
    title varchar unique
);

create table if not exists kitchen_type (
    id serial primary key,
    title varchar unique
);

create table if not exists kitchen (
    id serial primary key,
    type int not null ,
    constraint fk_kitchen_kitchen_type
        foreign key (type)
            references kitchen_type(id)
);

create table if not exists kitchen_worker(
    user_id int primary key ,
    kitchen_id int not null unique,
    status int not null ,
    constraint fk_kitchen_worker_kitchen_worker_status
        foreign key (status)
            references kitchen_worker_status(id),
    constraint fk_kitchen_worker_user_id
        foreign key (user_id)
            references system_user(id),
    constraint fk_kitchen_worker_kitchen_id
        foreign key (kitchen_id)
            references kitchen(id)
);

create table if not exists product(
    id serial primary key ,
    title varchar unique ,
    kitchen_producer_type_id int not null ,
    constraint fk_product_kitchen_type
        foreign key (kitchen_producer_type_id)
            references kitchen_type(id)
);

create table if not exists basket (
    order_id int,
    product_id int not null ,
    primary key (order_id, product_id),
    constraint fk_basket_order_id
        foreign key (order_id)
            references order_list(id),
    constraint fk_basket_product
        foreign key (product_id)
            references product(id)
)

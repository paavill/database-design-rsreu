DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create table if not exists user_role (
    title varchar primary key
);

create table if not exists user_status (
    title varchar primary key
);

create table if not exists system_user (
    id serial primary key,
    role_title varchar not null ,
    status_title varchar not null ,
    name varchar not null ,

    constraint fk_role_title
        FOREIGN KEY (role_title)
            REFERENCES user_role(title),
    constraint fk_status_title
        FOREIGN KEY (status_title)
            REFERENCES user_status(title)
);

create table if not exists order_status (
    title varchar primary key
);

create table if not exists order_list(
    id serial primary key ,
    user_id int not null ,
    status_title varchar not null ,
    constraint fk_order_user_id
        foreign key (user_id)
            references system_user(id),
    constraint fk_order_status
        foreign key (status_title)
            references order_status(title)
);

create table if not exists kitchen_worker_status(
    title varchar primary key
);

create table if not exists kitchen_type (
    title varchar primary key
);

create table if not exists kitchen (
    id serial primary key,
    type_title varchar not null ,
    constraint fk_kitchen_kitchen_type
        foreign key (type_title)
            references kitchen_type(title)
);

create table if not exists kitchen_worker(
    kitchen_id int,
    user_id int not null ,
    status varchar not null ,
    primary key (kitchen_id, user_id),
    constraint fk_kitchen_worker_kitchen_worker_status
        foreign key (status)
            references kitchen_worker_status(title),
    constraint fk_kitchen_worker_user_id
        foreign key (user_id)
            references system_user(id),
    constraint fk_kitchen_worker_kitchen_id
        foreign key (kitchen_id)
            references kitchen(id)
);

create table if not exists product(
    title varchar primary key ,
    kitchen_producer_type_title varchar not null ,
    constraint fk_product_kitchen_type
        foreign key (kitchen_producer_type_title)
            references kitchen_type(title)
);

create table if not exists basket (
    order_id int,
    product_name varchar not null ,
    primary key (order_id, product_name),
    constraint fk_basket_order_id
        foreign key (order_id)
            references order_list(id),
    constraint fk_basket_product
        foreign key (product_name)
            references product(title)
)

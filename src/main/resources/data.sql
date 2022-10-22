insert into user_role
values ('ADMIN'), ('USER');

insert into user_status
values ('OFFLINE'), ('ONLINE');

insert into order_status
values ('ACCEPT'), ('FINISHED');

insert into system_user
values (default, 'ADMIN', 'OFFLINE', 'Pavel');

insert into order_list
values (default, 1, 'ACCEPT');

insert into kitchen_type
values ('HOT');

insert into product
values ('pizza', 'HOT');

insert into basket
values (1,'pizza');

insert into kitchen_worker_status
values ('WORKING');

insert into kitchen
values (default, 'HOT');

insert into kitchen_worker
values (1, 1, 'WORKING')
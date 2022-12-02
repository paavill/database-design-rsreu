insert into user_role
values (default, 'ADMIN'), (default, 'USER');

insert into user_status
values (default, 'OFFLINE'), (default, 'ONLINE');

insert into order_status
values (default, 'ACCEPT'), (default, 'FINISHED');

insert into system_user
values (default, 1, 1, 'Pavel');

insert into order_list
values (default, 1, 1);

insert into kitchen_type
values (default, 'HOT');

insert into product
values (default, 'pizza', 1);

insert into basket
values (1,1);

insert into kitchen_worker_status
values (default, 'WORKING');

insert into kitchen
values (default, 1);

insert into kitchen_worker
values (1, 1, 1)
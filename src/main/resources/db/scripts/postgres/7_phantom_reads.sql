--first instance
BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
SELECT pg_sleep(20);
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;


--second instance
BEGIN TRANSACTION;
insert into product(name, amount, price) values("flowers", 33, 66);
commit;
--first instance
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
SELECT SLEEP(20);
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;


--second instance
START TRANSACTION;
insert into product(name, amount, price) values("flowers", 33, 66);
commit;
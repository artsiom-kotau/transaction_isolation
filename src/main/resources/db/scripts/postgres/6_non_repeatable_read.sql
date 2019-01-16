--first instance
BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
SELECT pg_sleep(20);
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;


--second instance
BEGIN TRANSACTION;
update product set price = 70;
commit;

--first instance
BEGIN TRANSACTION;
update product set price = 70;
UNLOCK TABLES;
SELECT pg_sleep(20);
rollback;

--second instance
BEGIN TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;



--first instance
START TRANSACTION;
update product set price = 70;
UNLOCK TABLES;
SELECT SLEEP(20);
rollback;

--second instance
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
START TRANSACTION;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;



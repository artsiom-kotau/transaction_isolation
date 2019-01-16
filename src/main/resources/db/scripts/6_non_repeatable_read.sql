--first instance
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
START TRANSACTION;
insert into product_order(product_id, amount, price)
select id, amount, price from product;
SELECT SLEEP(20);
insert into product_order(product_id, amount, price)
select id, amount, price from product;
commit;


--second instance
START TRANSACTION;
update product set price = 70;
commit;

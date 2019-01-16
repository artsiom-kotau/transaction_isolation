BEGIN TRANSACTION;
update product set price = price + 70;
commit;
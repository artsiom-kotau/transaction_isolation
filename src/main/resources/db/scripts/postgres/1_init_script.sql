drop table if exists product_order;
drop table if exists product;

CREATE TABLE product (
  `id` serial PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL
);

CREATE TABLE `product_order` (
  `id` serial PRIMARY KEY,
  `product_id` bigint NOT NULL,
  `amount` int(11) NOT NULL,
  `price` decimal(10,0) NOT NULL
);

insert into product(name, amount, price) values ('book', 100, 10);
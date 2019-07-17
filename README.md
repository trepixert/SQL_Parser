<h1>Ecwid тестовое задание - SQL parser</h1>

<h3>Программа для парса произвольного SELECT запроса.<h3>

Примечание:
Каждый оператор переводить на другую строку (включая те, что в подзапросе), например:

Как не надо:
1)
select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id
from product p
having p.product_id = 101;

2)
select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id from product p
having p.product_id = 101;

Как надо:
1)
select p.product_name, p.supplier_name, (select order_id
    from order_items
    having product_id = 102) as order_id
from product p
having p.product_id = 101;

2)
select *
from (select * 
  from A) a_alias

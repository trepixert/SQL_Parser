<h1>Ecwid тестовое задание - SQL parser</h1>

<h4>Программа для парса произвольного SELECT запроса.</h4>

В query.txt вставлять запрос, поскольку считывается с файла.
В query_example.txt представлены примеры SQL запросов.

Примечание:
Каждый оператор переводить на другую строку (включая те, что в подзапросе), например:

Как не надо:

1)
<pre>select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id <br>
from product p<br>
having p.product_id = 101;</pre>
2)
<pre>select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id from product p<br>
having p.product_id = 101;</pre>


Как надо:

1)
<pre>select p.product_name, p.supplier_name, (select order_id<br>
    from order_items<br>
    having product_id = 102) as order_id<br>
from product p<br>
having p.product_id = 101;</pre>
2)
<pre>select *<br>
from (select *<br> 
    from A) a_alias</pre>

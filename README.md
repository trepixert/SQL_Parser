<h1>Ecwid тестовое задание - SQL parser</h1>

<h4>Программа для парса произвольного SELECT запроса.</h4>

Примечание:
Каждый оператор переводить на другую строку (включая те, что в подзапросе), например:

Как не надо:
<ui>
    <li>1)
select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id <br>
from product p<br>
    having p.product_id = 101;
    </li>    
    <li>2)
select p.product_name, p.supplier_name, (select order_id from order_items having product_id = 102) as order_id from product p<br>
having p.product_id = 101;
    </li>
</ui>

Как надо:
<ui>
    <li>1)
select p.product_name, p.supplier_name, (select order_id<br>
    from order_items<br>
    having product_id = 102) as order_id<br>
from product p<br>
having p.product_id = 101;
    </li>
    <li>2)
<pre>select *<br>
from (select *<br> 
    from A) a_alias</pre>
    </li>
</ui>

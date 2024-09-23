-- SQL
-- Structured Query Language

-- DDL - DML ->
-- Data Definition Lang
-- Data Manipulation " -> Veri okuma,yazma..

-- * % _

-- * -> all
-- select [sonucta_gözükecek_alanlar] from [tablo_adı] [tablo_takma_adı]

select * from categories c

select category_id,category_name from categories c

-- filtreleme

select * from categories c where category_name = 'Seafood'

select * from categories c where category_id=5 or category_name='Seafood'

select * from categories c where category_id=8 and category_name='Seafood'

select * from categories c where (category_id=5 or category_name='Seafood') and description ='Seaweed and fish'

-- || &&

-- %
-- semboller ile kalıp üretmek.
-- buradan sonra kaç harf, hangi harf geliyor umursamamak.
select * from categories c where category_name like 'C%'  -- Con ile başlayan kategorileri getir.
select * from categories c where category_name like '%c%' -- içerisinde Con geçen kategorileri getir.

-- _
-- buradan sonra hangi harf geliyor umursamamak, kaç tane geldiği önemli!.
select * from categories c where category_name like 'Con________' -- Con ile başlayan 11 karakterli kategoriler.
--Condiments
--Confections


-- Aggregate Functions
select COUNT(distinct city) from customers c -- hangi şehirlerden müşterim var?
-- [91] -> [91]
--


-- ()
select * from customers c

-- avg,min,max,sum
select * from order_details od

-- sum => bir veri setinde seçilen sutunun tüm verilerinin toplamını
select sum(unit_price*quantity) from order_details od -- şu ana kadarki tüm şirket cirosunu hesapla

-- avg => ortalama
select avg(unit_price*quantity) from order_details od

-- min => en kücük veri
select min(unit_price*quantity) from order_details od

-- max => en büyük veri
select max(unit_price*quantity) from order_details od


-- tek tablo tüm isteğimi yerine getirmeyebilir
select * from orders where order_id=10248
select * from order_details od where order_id=10248
-- 2 çıktı tek veri setinde birleştirmek

-- JOIN
select * from orders o inner join order_details od on o.order_id = od.order_id where o.order_id=10248
select * from orders o left join order_details od on o.order_id = od.order_id where o.order_id=10248
select * from orders o right join order_details od on o.order_id = od.order_id where o.order_id=10248
select * from orders o full outer join order_details od on o.order_id = od.order_id where o.order_id=10248
--

select * from orders o
                  inner join order_details od
                             on o.order_id = od.order_id
                  inner join products p
                             on od.product_id  = p.product_id
                  inner join categories c
                             on p.category_id = c.category_id
where o.order_id=10249

--- Group By
-- HAVING

select country, COUNT(*) from customers c
group by country
having count(*) >= 5

-- çalışan başına toplam satış tutarı
-- e.first_name || ' ' || e.last_name as EmployeeName
select
    e.employee_id ,
    e.first_name || ' ' || e.last_name as EmployeeName ,
    SUM(od.quantity * od.unit_price) as TotalSales
from employees e
         inner join orders o on o.employee_id = e.employee_id
         inner join order_details od on od.order_id = o.order_id
group by e.employee_id ,e.first_name, e.last_name
order by TotalSales desc


-- yıl ve ay bazında satış tutarları
-- alias
select date_part('year',o.order_date) as "Yıl", date_part('month',o.order_date) as "Ay",
       SUM(od.quantity * od.unit_price) as "Toplam Satış"
from order_details od
         inner join orders o
                    on od.order_id = o.order_id
group by date_part('year', o.order_date),date_part('month', o.order_date)
having SUM(od.quantity * od.unit_price) > 100000
order by "Toplam Satış" desc


-- recursive query
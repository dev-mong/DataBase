--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.
--(5) 박지성이 구매한 도서의 출판사 수
select count(*)
from book
where bookid in (select bookid from orders where custid=1);

--풀이))
select count(distinct b.publisher)
from customer c, orders o, book b
where c.custid=o.custid and o.bookid=b.bookid and c.name='박지성';

select * from orders o, customer c where o.custid=c.custid and c.name='박지성';

select count(distinct publisher)
from book
where bookid in(select distinct o.bookid from orders o, customer c where o.custid=c.custid and c.name='박지성')
;

-- (6) 박지성이 구매한 도서의 이름, 가격, 정가와 판매 가격의 차이
select b.bookname, abs(b.price-o.saleprice)
from book b, orders o 
where b.bookid=o.bookid and o.custid=1
;

--풀이))
select b.bookname, b.price, abs(b.price-saleprice) as priceGap
from customer c, orders o, book b
where c.custid=o.custid and o.bookid=b.bookid and c.name='박지성';

select b.bookname, b.price, abs(b.price-o.saleprice) as priceGap
from orders o, book b
where o.bookid=b.bookid and o.custid=(select custid from customer where name='박지성');


-- (7) 박지성이 구매하지 않은 도서의 이름
select distinct b.bookname
from orders o, book b
where not(custid in (select custid from orders where custid=1))
and o.bookid=b.bookid
order by b.bookname desc
;

--풀이)) ??????
select  b.bookname
from orders o, book b
where b.bookid not in (select bookid from orders o, customer c where name='박지성' and o.custid=c.custid)
order by b.bookname desc
;


--2 마당서점의운영자와경영자가요구하는다음질문에대해SQL 문을작성하시오.
--(8) 주문하지않은고객의이름(부속질의사용)
select name
from customer
where not(custid in(select custid from orders))
;

--풀이))


--(9) 주문금액의총액과주문의평균금액
select sum(saleprice) as total, trunc(avg(saleprice)) as avg
from orders
;

--(10) 고객의 이름과 고객별 구매액
select name, sum(o.saleprice)
from customer c, orders o
where c.custid=o.custid
group by name
;

--(11) 고객의 이름과 고객이 구매한 도서목록
select c.name, b.bookname 
from customer c,orders o, book b
where c.custid=o.custid and o.bookid in (select bookid from orders) 
and b.bookid=o.bookid
order by c.name
;

--(12) 도서의가격(Book 테이블)과판매가격(Orders 테이블)의차이가 가장 많은 주문
select * from orders;
select * from book;

select *
from orders o, book b
where o.bookid=b.bookid and abs(o.saleprice-b.price) >= 
all( select abs(o.saleprice-b.price) from orders o, book b where o.bookid=b.bookid)
;

--(13) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select trunc(avg(saleprice)) from orders;
select custid, trunc(avg(saleprice)) from orders group by custid;

select c.name, avg(o.saleprice)
from customer c, orders o
where c.custid=o.custid 
group by c.name
having avg(o.saleprice) > (select avg(saleprice) from orders)
;


--3. 마당서점에서 다음의 심화된 질문에 대해 SQL 문을 작성하시오.
--(1) 박지성이 구매한 도서의 출판사와 같은 출판사에서 도서를 구매한 고객의 이름

select c.name
from customer c, orders o
where bookid in (select bookid from orders where custid=1) and c.custid=o.bookid 
and c.name != '박지성'
;


--(2) 두 개 이상의 서로 다른 출판사에서 도서를 구매한 고객의 이름
select c.name
from customer c, orders o, book b
where c.custid=o.custid and o.bookid=b.bookid 
group by c.name
having count(distinct b.publisher) >= 2
order by c.name 
;

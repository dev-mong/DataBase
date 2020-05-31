--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.
--(5) 박지성이구매한도서의출판사수
select custid from customer where name='박지성';

select count(distinct publisher)
from orders o, book b
where custid = (select custid from customer where name='박지성')
and o.bookid=b.bookid
;

select count(distinct publisher)
from book 
where bookid in (select bookid from orders o, customer c where o.custid=c.custid and c.name='박지성')
;

--(6) 박지성이구매한도서의이름, 가격, 정가와판매가격의차이
select b.price-o.saleprice from orders o, book b where o.bookid=b.bookid;

select bookname, price, b.price-o.saleprice as salgap
from orders o, book b
where custid = (select custid from customer where name='박지성')
and o.bookid=b.bookid
;

select  bookname, price, b.price-o.saleprice as salgap
from orders o, book b, customer c
where o.custid=c.custid and o.bookid=b.bookid
and c.name='박지성';


--(7) 박지성이 구매하지않은 도서의 이름
select b.bookname
from book b
where b.bookid not in (select o.bookid from orders o, customer c where o.custid=c.custid and c.name='박지성');

select  distinct b.bookname
from orders o, book b
where b.bookid not in (select bookid from orders o, customer c where name='박지성'
and o.custid=c.custid)
order by b.bookname desc
;


--2 마당서점의운영자와경영자가요구하는다음질문에대해SQL 문을작성하시오.
--(8) 주문하지 않은 고객의 이름(부속질의사용)
select name
from customer
where custid not in (select distinct custid from orders)
;

select c.name
from orders o, customer c
where o.custid(+)=c.custid
and o.orderid is null
;


--(9) 주문 금액의 총액과 주문의 평균 금액
select sum(saleprice) as sum, trunc(avg(saleprice)) as avg
from orders
;

--★(10) 고객의 이름과 고객별 구매액

select c.name, sum(o.saleprice)
from customer c, orders o
where c.custid=o.custid
group by c.name;

--(11) 고객의 이름과 고객이 구매한 도서 목록
select c.name, b.bookname
from customer c, orders o, book b
where o.custid=c.custid and o.bookid=b.bookid
order by c.name
;

--(12) 도서의가격(Book 테이블)과 판매가격(Orders 테이블)의 차이가 가장 많은 주문
select *
from book b, orders o
where b.bookid=o.bookid
and abs(b.price-o.saleprice) >= 
all (select abs(b.price-o.saleprice) from book b, orders o where b.bookid=o.bookid)
;

--★(13) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select avg(saleprice) from orders;
select avg(o.saleprice) from orders o, customer c where o.custid=c.custid
group by c.custid;

select c.name
from orders o, customer c 
where o.custid=c.custid 
group by c.name
having avg(o.saleprice) > (select avg(saleprice) from orders)
;

--3. 마당서점에서 다음의 심화된 질문에 대해 SQL 문을 작성하시오.
--(1) 박지성이 구매한 도서의 출판사와 같은 출판사에서 도서를 구매한 고객의 이름
select distinct b.publisher
from book b, orders o, customer c
where b.bookid=o.bookid and c.custid=o.custid
and c.name='박지성'
;

select c.name
from book b, orders o, customer c
where b.bookid=o.bookid and c.custid=o.custid
and b.publisher in (select distinct b.publisher
from book b, orders o, customer c
where b.bookid=o.bookid and c.custid=o.custid
and c.name='박지성'
) and c.name != '박지성'
;


--(2) 두 개 이상의 서로 다른 출판사에서 도서를 구매한 고객의 이름
select c.name
from customer c, orders o, book b
where c.custid=o.custid and o.bookid=b.bookid
group by c.name
having count(distinct b.publisher) >= 2
order by c.name;




-----------------------------
-- sub query
-----------------------------


--평균 급여를 구하는 쿼리문을 서브 쿼리를 사용하여
-- 평균 급여보다 더 많은 급여를 받는 사원을 검색

select * 
from emp
where  sal> (select avg(sal)from emp)
;


select *
from emp 
where deptno = (select deptno from emp where ename='SCOTT')
;


--다중행 서브쿼리
--1_ IN 연산자
-- 예제))3000 이상 받는 사원이 소속된 부서(10번, 20번)와 
--동일한 부서에서 근무하는 사원이기에 서브 쿼리의 결과 중에서 하나라도
--일치하면 참인 결과를 구하는 IN 연산자와 함께 사용
select *
from emp
where deptno in (20,30)
;
select * from emp where sal>=3000;

select *
from emp
where deptno in(select deptno from emp where sal>=3000)
;

--2_ALL 연산자
select ename, sal
from emp
where sal>all(select sal from emp where deptno=30)
;

--3_ ANY 연산자
--부서번호가 30번인 사원들의 급여 중 가장 작은 값(950)보다 많은
--급여를 받는 사원의 이름, 급여를 출력
select min(sal) from emp where deptno=30;
select ename, sal from emp where sal>950;

select ename, sal 
from emp 
where sal>(select min(sal) from emp where deptno=30);

select ename, sal 
from emp 
where sal>any(select sal from emp where deptno=30);


--ROUNUMM : 입력순서의 번호
select rownum, ename
from emp;


--예제)))
--마당서점의 고객별 판매액을 보이시오(결과는 고객이름과 고객별 판매액을 출력)
select name
from orders o, customer c
where o.custid=c.custid
group by name
;
select custid,
(select name 
from customer c
where o.custid=c.custid
)
as customer_name, sum(saleprice)
from orders o
group by custid
;

--인라인 뷰 -from 부속질의
--예제)))
--고객 번호가 2이하인 고객의 판매액
--결과는 고객이름과 고객별 판매액 출력
select * 
from customer
where custid<=2
;

select cs.name, sum(od.saleprice)
from (select * 
from customer
where custid<=2) cs, orders od 
where cs.custid=od.custid
group by cs.name
;


select rownum,ename,empno
from (select ename, empno, job, deptno from emp order by ename)
;


--예제))
--평균 주문금액 이하의 주문에 대해서 주문번호와 금액을 보이시오.
select orderid,saleprice
from orders
where saleprice <= (select avg(saleprice)from orders)
;

--각 고객의 평균 주문금액보다 큰 금액의 주문 내역에 대해서 주문번호, 고객번호, 금액을 출력
select orderid, custid, saleprice
from orders
where  (select avg(saleprice)from orders) < saleprice
;

--대한민국에 거주하는 고객에게 판매한 도서의 총판매액을 구하시오
select * from customer where address like '%대한민국%';
select sum(saleprice)
from orders
where custid in(select custid from customer where address like '%대한민국%')
;

--3번 고객이 주문한 도서의 최고 금액보다 더 비싼 도서를 구입한 주문의 주문번호와 금액 출력
select * from orders where custid=3;
select max(saleprice) from orders where custid=3;
select orderid, saleprice
from orders
where saleprice > all(select saleprice from orders where custid=3)
;

--EXISTS 연산자로 대한민국에 거주하는 고객에게 판매한 도서의 총 판매액을 출력
select sum(saleprice) 
from orders
where EXISTS (select * from customer where address like '%대한민국%' and customer.custid=orders.custid)
;




-----------------------
-- JOIN
-----------------------

select * from emp;
select * from dept;

-- 1_cross JOIN
select *
from emp,dept
;

--2_ equi join
select e.ename, d.dname,e.deptno,d.deptno
from emp e,dept d
where e.deptno=d.deptno
;
--예제))
--book,customer, orders
select * from orders;
select * from book;
select * from customer;
--cross JOIN
select *
from orders o, book b, customer c
;

select *
from orders o, book b, customer c
where o.bookid=b.bookid and o.custid=c.custid
;

--예제 (3) 박지성의총구매액(박지성의고객번호는1번으로놓고작성)
select sum(saleprice)
from orders o, book b, customer c
where o.bookid=b.bookid and o.custid=c.custid and c.name='박지성'
;


--3_ Non-Equi JOIN
select * from emp;
select * from salgrade;

select e.ename, e.sal, s.grade
from emp e, salgrade s
--where e.sal>=s.losal and e.sal<=s.hisal
where sal between s.losal and s.hisal
;

select * from emp;
--4_ self join
--관리자의 이름을 출력
select e.ename || '의 상사는' || m.ename || '입니다.'
from emp e,emp m
where e.mgr=m.empno
;


--5_ outer join
select e.ename || '의 상사는' || m.ename || '입니다.'
from emp e,emp m
where e.mgr=m.empno(+) 
;

select e.ename, e.empno, e.sal, e.comm, m.ename, nvl(m.ename,'관리자없음')
from emp e, emp m
where e.mgr=m.empno(+)
;


---------------------
--ansi join
----------------------

--cross join
select * from emp cross join dept;

--inner join
--on절 조건문
select e.ename,d.dname
from emp e inner join dept d
on e.deptno=d.deptno
;
-- using절 조건문 : 공통 컬럼이므로 using 사용  
select ename,dname
from emp inner join dept
using(deptno)
;

--natual join
select *
from emp natural join dept
;

select ename,dname
from emp natural join dept
;

--outer join
select *
from emp e left outer join emp m
on e.mgr=m.empno
;






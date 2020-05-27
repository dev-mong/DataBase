

-------------------------------------------------
-- VIEW
--------------------------------------------------
-- 논리적인 가상 테이블 : 테이블과 같이 사용 가능,  제약이 있음.
-- 물리적인 테이블 기반으로 만듦 : SELECT 서브쿼리를 이용해서 만듦.

--CREATE [OR REPLACE] VIEW VIEW이름 AS 서브쿼리 
--예제)) 30번 부서에 소속된 사원들의 사번과 이름, 부서번호를 출력
-- SELECT 문을 하나의 뷰로 정의
create view emp_view_deptno30
as
select empno, ename, deptno from emp where deptno=30;

select * from emp_view_deptno30;
desc user_views;
select view_name ,text from user_views;

--인라인 뷰를 사용해서 입사일이 빠른 사람 5명만 가져오기

--1. view 객체 생성
create or replace view emp_hir_view
as 
select empno, ename, hiredate from emp order by hiredate asc;  

select rownum, empno, ename, hiredate  from emp_hir_view;

select rownum, empno, ename, hiredate from emp_hir_view where rownum<=5;
select rownum, empno, ename, hiredate from emp order by hiredate;  















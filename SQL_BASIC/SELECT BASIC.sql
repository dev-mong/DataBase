--emp table  : 사원정보
--dept table : 부서정보
--bonus table: 임시 테이블
--salgrade table:급여 정보 

select * from dept;


-- 로그인한 계정이 소유한 테이블 확인
select * from tab;

-- 테이블 구조 확인 : desc 테이블 이름
desc emp;
desc dept;
desc salgrade;

select * from emp;
select * from dept;

-- 데이터 검색 질의
select ename,sal, deptno, empno -- 컬럼 이름을 나열
from emp --테이블 이름
;

select deptno from dept;
select deptno, dname from dept;


-- select의 결과는 새로운 테이블이다
-- 컬럼의 산술연산이 가능하다 . +, -, *,/ mod 함수 이용(나머지)
select *from emp;
select ename, sal,sal + comm
from emp;

select ename, sal, sal+500
from emp;

select ename, sal, sal-100
from emp;

select ename, sal, sal*12
from emp;

select ename, sal, sal/2
from emp;

-- null 값의 확인
select ename, job, sal, comm, sal*12, sal*12+comm
from emp;

-- nvl(컬럼명, 기본값): null 값 치환 함수 
-- 기본값은 컬럼의 도메인의 자료형과 같아야 한다. 
select ename, job, sal, nvl(comm,0), sal*12 as sal_1, sal*12+nvl(comm,0) as total
from emp;


-- DB의 문자열 표현 -> 작은 따옴표를 이용
-- EX) '문자열'
-- 문자열을 붙여서 출력하는 연산 -> || 
select job, ename || ' is a' || job as msg
from emp;

-- 출력 컬럼의 중복을 제거하고 하나씩만 출력 : DISTINCT
select deptno from emp;
select distinct deptno from emp;

select deptno, job from emp order by deptno;
select distinct deptno, job from emp order by deptno;


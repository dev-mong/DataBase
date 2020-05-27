-----------------------
--DML
-----------------------

drop table dept01;

create table dept01
as
select * from dept where 1<0;

desc dept01;

-- 새로운 부서 정보를 입력: 행단위 입력
-- insert into 테이블 이름 (입력하고자 하는 컬럼들) values(데이터들)
-- 입력 컬럼의 순서와 입력 데이터의 순서는 같아야한다.
insert into dept01 (deptno, dname, loc) values (10, 'MANAGER', 'SEOUL');
insert into dept01 values(20,'DESIGN', 'BUSAN');
insert into dept01 (loc, deptno, dname) values('London',30, 'BUSAN');
insert into dept01 (deptno, dname) values(40,'DEV');

desc dept01;
select * from dept01;


create table dept02(
    deptno number(2) not null,
    dname varchar2(20) not null,
    loc varchar2(20) default 'SEOUL' 
);
insert into dept02 (deptno, dname, loc) values (10, 'MANAGER', 'SEOUL');
insert into dept02 (deptno, dname) values(40,'DEV');
insert into dept02  values(40,'DEV'); -- 오류 : 입력 할 컬럼 수가 다름

--loc null이 아닐때 삽입 가능
--alter table dept02 modify(loc varchar2(30) default'SEOUL' null); 
insert into dept02 (deptno, dname, loc) values (20, 'MANAGER', null); --null 값으로 입력됨
insert into dept02 (deptno, dname, loc) values (30, 'MANAGER', ''); --null 값으로 입력
insert into dept02 (deptno, dname) values (30, 'MANAGER', ''); -- 오류 발생, 컬럼 목록 수가 다름 
delete from dept02 where dname='test';
--loc not null일 때 
alter table dept02 modify(loc varchar2(20) default'SEOUL' not null); --제약 조건 변경
insert into dept02 (deptno ,dname, loc) values(60,'test', 'locTest');--불가능
insert into dept02 (deptno, dname, loc) values (30, 'MANAGER', ''); --추가 불가능

select * from dept02;
desc dept02;



--서브쿼리를 이용해서 여러 테이블에 한번에 데이터 삽입
--테스트 테이블 emp_hir : empno, ename, hiredate
-- 테스트 테이블 emp_mgt : empno, enmae, mgr

--emp 테이블의 구조를 가져옴 
create table emp_hir
as
select empno, ename, hiredate from emp where 1<0;

create table emp_mgr
as 
select empno, ename, mgr from emp where 1<0;

desc emp_hir;
desc emp_mgr;


--  두 개의 테이블에 emp 테이터를 기반으로 삽입 ◆   
insert all
into emp_hir values(empno, ename, hiredate)
into emp_mgr values(empno, ename, mgr)
select empno, ename, hiredate, mgr
from emp
;

select * from emp_hir;
select * from emp_mgr;


-- 예제)) INSERT ALL 명령문에 
--WHEN 절을 추가해서 조건을 제시하여 조건에 맞는 행만 추출하여 테이블에 추가합니다
--EMP_HIR02 테이블에는 1982 년 01 월01 일 이후에 입사한 사원들의 번호, 사원 명, 입사일을 추가
--EMP_SAL 테이블에는 급여가 2000 이상인 사원들의 번호, 사원 명, 급여를 추가

create table emp_hir02
as 
select empno,ename, hiredate from emp where 1<0;

create table emp_sal
as
select empno,ename,sal from emp where 1<0;

desc emp_hir02;
desc emp_sal;

insert all
when hiredate > '82/01/01' then
    into emp_hir02 values(empno, ename, hiredate)
when sal>=2000 then
    into emp_sal values(empno, ename, sal)
select empno, ename, hiredate, sal from emp 
;





----------------------------
--DML 수정, 삭제
-----------------------------

--UPDATE
drop table emp01;

create table emp01
as
select * from emp
;

-- 컬럼 데이터 변경( 수정 )
-- UPDATE 테이블 이름 SET [컬럼이름1]=값, [컬럼이름2]=값, .... WHERE 행을 찾는 조건식

-- 모든행 컬럼 수정
-- WHERE 절이 없는 경우 테이블의 모든 행이 영향을 받음 
select * from emp01;

--예제 1)) 모든 사원의 부서번호를 30번으로 수정
update emp01
set deptno=30
;
select * from emp01;

--예제 2)) 모든 사원의 급여를 10% 인상( UPDATE문 )
update emp01
--set sal=sal*0.1
set sal = sal * 1.1
;
select * from emp01;

-- 예제 3)) 모든 사원의 입사일을 오늘 날짜로 수정
update emp01
set hiredate=sysdate
;

update emp 
set hiredate=sysdate;
select * from emp;

commit;

rollback;

select * from emp01;

drop table emp01;


--특정 행만 변경할 때 
--예제 1)) 부서번호가 10번인 사원의 부서번호를 30번으로 수정
update emp01 
set deptno=30
where deptno=10;
select * from emp01;

-- 예제 2)) 급여가 3000 이상인 사원만 급여를 10% 인상
update emp01
set sal=sal*1.1
where sal>=3000
;
select * from emp01;

--예제 3)) 1987년에 입사한 사원의 입사일이 오늘로 수정

-- => 87년 입사한 사람 출력하는 방법 1
select * from emp 
where hiredate between '1987/01/01' and '1987/12/31';
-- => 87년 입사한 사람 출력하는 방법 2
select * from emp
where substr(hiredate,1,2)='87';

update emp01
set hiredate=sysdate
where substr(hiredate,1,2)='87'
;
select * from emp01;

-- 두개 이상의 컬럼 값 변경
--예제 1)) SCOTT 사원의 부서번호는 20번으로, 직급은 MANAGER로 한꺼번에 수정
update emp01
set deptno=10, job='MANAGER'
where ename='SCOTT';

select * from emp where ename='SCOTT';
select * from emp01 where ename='SCOTT'; --직급 변경 

--예제 2)) SCOTT 사원의 입사일자는 오늘로, 급여를 50 으로 커미션을 4000 으로 수정
update emp01
set sal=50, comm=4000, hiredate=sysdate
where ename='SCOTT';
select * from emp01 where ename='SCOTT';


--서브 쿼리를 이용하여 두 개 이상의 컬럼값 변경
-- 예제 1)) 20번 부서의 지역명을 40번 부서의 지역명으로 변경하기 위해서 서브 쿼리문을 사용해 봅시다 => 한개의 컬럼 변경

--  방법1
select * from dept01;
drop table dept01;

create table dept01
as
select * from dept;

update dept01
set loc=(
    select loc from dept01 where deptno=40
)
where deptno=20
;

--예제 2))부서번호가 20인 부서의 부서명과 지역명을 부서 번호가 40번인 부서와 동일하게 변경 => 두개 컬럼 변경
update dept01
set dname=(select dname from dept01 where deptno=40),
    loc=(select loc from dept01 where deptno=40)
where deptno=20;

update dept01
set (dname, loc)=(select dname, loc from dept01 where deptno=40)
where deptno=20;

select * from dept01;
rollback;



-----------------------------------
--DML 삭제
------------------------------------
--DELETE FROM 테이블 이름 WHERE 행을 찾을 조건식

--모든 행을 삭제한다 
--WHERE 절이 없으면 모든행에 영향을 받음
--예제 ) 부서 테이블의 모든 행을 삭제
delete from dept01;
select * from dept01;

-- 특정 행 만 삭제 - where절 이용
--예제 )) 부서 테이블에서 30번 부서만 삭제 
rollback;
delete from dept01 where deptno=30;
select * from dept01;

--서브 쿼리를 이용해서 행 삭제
--예제 ))사원 테이블에서 부서명이 SALES인 사원을 모두 삭제
rollback;
select deptno from dept where dname='SALES';
select * from emp01;

delete from emp01 where deptno = (
    select deptno from dept where dname='SALES'
);
select * from emp01;































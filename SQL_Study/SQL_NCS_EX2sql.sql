drop table contact;

--1.DDL
create table contact(
    pidx number(6) constraint contact_pidx_pk primary key, 
    name varchar2(10) not null,
    phonenumber number(12) not null,
    address varchar2(20) default '입력없음' not null,
    email varchar2(10) default '입력없음' not null,
    type varchar2(5) constraint contact_type_check check(type in('univ','com','cafe')), 
    major varchar2(10),
    grade number(1),
    comname varchar2(15),
    comdept varchar2(10),
    comjob varchar2(10),   
    cafename varchar2(10),
    cafenick varchar2(10)
);

2. DEPT 테이블에 데이터를 삽입하는 SQL을 작성하시오. 입력 데이터는 임의로 작성하시오.
insert into dept (deptno,dname,loc) values (50,'program','JEJU');


3. DEPT 테이블에 위에서 삽입한 데이터의 dname, loc 데이터를 변경하는 SQL을 작성하시오.

입력 데이터는 임의로 작성하시오.

update dept set dname='design', loc='SEOUL' where dname='program' and loc='JEJU';


4. DEPT 테이블에 위에서 삽입한 데이터를 deptno 컬럼의 값을 기반으로 삭제하는 SQL을 작성하시오.
delete from dept where deptno=50;

5. 사용자가 보유한 테이블 목록을 확인하는 SQL문을 작성하시오.
select * from tab;

6. EMP 테이블의 구조를 확인하는 SQL을 작성하시오.
desc emp;

7. 사용자가 정의한 제약조건들을 확인하는 SQL문을 작성하시오.
select * from user_constraints ;

#2 아래 요구사항에 맞도록 고급 SQL 문을 작성하시오.

1. EMP 테이블의 ename 컬럼에 인덱스를 생성하는 SQL을 작성하시오. 인덱스의 이름은 emp_index
create index emp_index on emp(ename);

2. EMP 테이블과 DEPT 테이블을 조인하는 SQL을 기반으로 view 객체를 생성하는 SQL을 작성하시오.
view 의 이름은 emp_view 로 하시오.
create view emp_view
as
select e.empno,e.ename,e.job,e.hiredate,e.mgr,e.sal,e.comm,e.deptno,d.dname,d.loc from emp e, dept d where e.deptno=d.deptno;


3. EMP 테이블에서 모든 사원의 부서번호를 이름이 'SCOTT'인 사원의 부서번호로 변경하는 SQL을 작성하시오.
update emp set deptno=(select deptno from emp where ename='SCOTT');


select * from user_objects where object_type='VIEW';




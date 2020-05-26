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





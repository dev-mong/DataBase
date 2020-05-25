------------------------
-- 테이블 생성
------------------------

--create table 테이블 이름 () : 괄호 안에 컬럼을 정의
--(컬럼명1 타입(사이즈), 컬럼명2 타입(사이즈), ..... )

-- ddl_test 라는 이름의 테이블을 생성
-- 컬럼 1: no, 숫자 타입, 사이즈 3
-- 컬럼 2: name, 가변 문자열 저장, 사이즈 10
-- 컬럼 3: birth,  날짜 타입, 기본값은 현재 날짜와 시간
create table ddl_test(
    no number(3),
    name varchar2(10),
    birth date default sysdate
);

--테이블 생성 확인 
desc ddl_test;
select * from tab;
select * from ddl_test;

--데이터 추가 
insert into ddl_test (no, name) values(1,'scott');

--emp 테이블 참조
--사원번호, 사원이름, 관계 3개의 컬럼으로 구성 된 emp 01 테이블 생성
desc emp;
create table emp01(
    empno number(4),
    ename varchar2(10),
    sal number(2,7)
);

select * from emp;
desc emp01;


--테이블 복사: 서브 쿼리 이용 -> 스키마 복사,행 복사, 제약조건의 복사는 되지않음
create table emp02
as 
select * from emp;

select * from emp02;

--emp 테이블의 empno와 ename 컬럼만 복사해서 새로운 테이블 emp03을 생성
create table emp03 
as
select empno, ename from emp;
select * from emp03;

--테이블의 테이터 복사
create table emp04
as
select * from emp where deptno=10;
select * from emp04;

-- 테이블의 구조만 가져옴
create table emp05
as
select * from emp where 1=0;
select * from emp05;



--테이블에 컬럼 추가
--alter table 테이블명 add (컬럼 정의)
desc emp01;

alter table emp01 add (job VARCHAR2(10));
desc emp01;

--컬럼의 변경
--alter table modify (컬럼정의)
--예제)) job 컬럼을 글자 길이 30으로 변경, null 제약 
alter table emp01 MODIFY (job varchar(10) not null);


--컬럼의 삭제
--alter table 테이블 이름 drop column 컬럼이름
alter table emp01 drop column sal;
select * from emp01;


-----------------------
--테이블 객체 삭제
-----------------------
--drop table 테이블 명
drop table emp01;
select * from tab;

TRUNCATE TABLE EMP02;
select * from emp02;

--테이블 이름의 변경
-- rename 현재 테이블 이름 to 새로운 테이블 이름
rename emp02 to test_emp;
select * from tab;
select * from test_emp;


--제약 조건 : 기본키
desc dept;
insert into dept values(10,'test','seoul');

--컬럼의 제약 정의는 컬럼 정의를 하면서 컬럼 옆에 정의
--컬럼의 타입을 모두 정의하고, 아래에 제약을 정의하는 방법
--create table emp01(
--    empno number(4) not null
--);
--create table emp01(
--    empno number(4) primary key,
--    primary key(empno) -- 제약사항 정의
--);



-- NOT NULL 제약의 경우 : 컬럼의 값에 NULL값을 허용하지 않음
--                      컬럼 레벨에서만 정의가 가능
-- 사원 테이블(EMP02)을
-- 사원번호, 사원명, 직급, 부서번호 4개의 컬럼을 구성
-- 사원번호와 사원명에 NOT NULL 조건을 지정
drop table emp02;
create table emp02( 
    empno number(4) NOT NULL,
    ename varchar2(10) NOT NULL,
    job varchar2(10),
    deptno number(2)
);

desc emp02;

insert into emp02 values(null, null,'MANAGER', 10);
insert into emp02 values(1111, null,'MANAGER', 10);
insert into emp02 values(1111, 'SON','MANAGER', 10);
SELECT * FROM EMP02;

drop table emp03;
create table emp03(
    empno number(4) unique,
    ename varchar2(10) not null,
    job varchar2(10),
    deptno number(2)
);
desc emp03;
insert into emp03 values(1111,'test','MANAGER',10);
insert into emp03 values(1111,'test123','MANAGER',20); --unique 제약 조건 위배 
insert into emp03 values(NULL,'test123','MANAGER',20); 

drop table emp03;
create table emp03(
    empno number(4) unique not null,
    ename varchar2(10) not null,
    job varchar2(10),
    deptno number(2)
);
insert into emp03 values(1111,'test','MANAGER',10);
insert into emp03 values(1111,'test123','MANAGER',20); --unique 제약 조건 위배 
insert into emp03 values(NULL,'test123','MANAGER',20); 


--예제)) 사원 테이블과 유사한 구조의 사원번호, 사원명,직급, 부서번호 
--4개의 칼럼으로 구성된 EMP04 테이블을 생성하되
--사원번호에는 유일키로 사원명은 NOT NULL 제약조건
drop table emp04;
create table emp04(
    empno number(4) constraint emp04_empno_uk unique constraint emp04_empno_nn not null,
    ename varchar2(10) constraint emp04_ename_nn not null,
    job varchar2(10),
    deptno number(2)
);
desc emp04;
insert into emp04 values(1111,'test','MANAGER',10);
insert into emp04 values(1111,'test123','MANAGER',20); --unique 제약 조건 위배 
insert into emp04 values(NULL,'test123','MANAGER',20); 



-----------------------------------------------------
-- INDEX
-----------------------------------------------------

-- 검색을 빠르게 하기 위해 사용한다.
-- DML 작업에는 성능 저하가 올 수 있다.

DESC user_ind_columns;

select index_name, table_name, column_name
from user_ind_columns;


--테스트 테이블 생성
drop table emp01;

create table emp01
as
select  * from emp;

insert into emp01 select * from emp01;
select count(*) from emp01;

insert into emp01 (empno, ename) values(1111,'SON');
insert into emp01 (empno, ename) values(2222,'PARK');

select * from emp01 where ename='PARK';
select * from emp01 where ename='SON';

-- INDEX 생성
-- create index 인덱스이름 on 테이블이름 (컬럼이름)


create index emp01_ename_index on emp01(ename);
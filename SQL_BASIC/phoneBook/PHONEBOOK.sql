--------------------------------
--전화번호부
--------------------------------

--사용자 정의 제약조건을 저장하는 딕셔너리 테이블
desc user_constraints;
select CONSTRAINT_NAME from user_constraints;
select CONSTRAINT_NAME from user_constraints where table_name='phoneinfo_basic' ;
select * from user_constraints;
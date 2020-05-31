
===== 전화번호 부( Contact )
-- 대리키 : 일련번호 -> pIdx
-- 이름, 전화번호, 주소, 이메일 <- 기본정보
-- 주소값과 이메일은 입력이 없을 때 기본값 입력
-- 친구의 타입 (카테고리) : univ, com, cafe 세가지 값만 저장 가능
------ 선택 정보
-- 전공, 학년
-- 회사이름, 부서이름, 직급
-- 모임이름, 닉네임

--컬럼 레발 제약 정의
create table phonebook
(
    pidx number(4) constraint phonebook_pidx_pk primary key,
    pname varchar2(10) not null,
    pphonenumber varchar2(13) not null,
    paddress varchar2(20) default '입력없음',
    pemail varchar2 (10) default '입력없음',
    ptype varchar2(10) not null constraint phonebook_ptype_check check (ptype in('univ','com','cafe')),
    pmajor varchar2(10),
    pgrade number(1) constraint phonebook_pgrade_check check (pgrade between 1 and 4),
    pcompany varchar2(20) default 'N' not null,
    pdeptname varchar2(20) not null,
    pjob varchar2(10),
    pcafename varchar2(10),
    pcafenick varchar2(20)
);

desc phonebook;
alter table phonebook add(test varchar2(10));
alter table phonebook modify(test varchar2(20));
alter table phonebook drop(test);
rename phonebook to phonebooktest;
rename phonebooktest to phonebook;  
desc phonebook;


--1. 회사 친구 정보 변경
-- 기본정보 + 회원정보 수정
-- 기본정보 수정
-- 회사 정보 수정
update phoneInfo_basic
set fr_name ='SCOTT', fr_phonenumber='010-1689-7201', fr_address='SEOUL', fr_email='scott@'
where p_idx=3;

update phoneInfo_com
set fr_c_company='KAKAKO' 
where idx=1
;
--회사 친구 정보 삭제
select * from phoneInfo_com;
delete phoneInfo_com where fr_ref=3;
delete phoneInfo_univ where fr_ref=3;
delete phoneInfo_basic where p_idx=3;

--대학 친구 정보 삭제
select * from phoneInfo_univ;
delete phoneInfo_univ where fr_ref=2;
delete phoneInfo_com where fr_ref=2;
delete phoneInfo_basic where p_idx=2;

select * from phoneInfo_basic b, phoneInfo_com c where b.p_idx=c.fr_ref;

select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref(+) and b.p_idx=u.fr_ref(+) order by p_idx;

rollback;








--------------------------------------------
--외래키 설정 
---------------------------------------------
--외래키 설정시 부모의 행이 삭제 될 때 설정
--REFERENCE phoneInfo_basic(p_idx) on delete 설정 옵션 
--no action : 모두 삭제 불가 
--cascade : 참조를 하고 있는 자식 테이블의 모든 행도 삭제
--set null : 참조를 하고 있는 자식 테이블의 모든 행의 외래키 컬럼의 값을 null로 변경
--set default : 참고를 하고 있는 자식 테이블의 모든 행의 외래키 컬럼의 값을 기본값으로 변경


drop table phoneInfo_basic;
drop table phoneInfo_univ;
drop table phoneInfo_com;

create table phoneInfo_univ(
    idx number(6),
    fr_u_major varchar2(20) default 'N'  constraint fr_u_major_mm not null ,
    fr_u_year number(1) default '1'constraint fr_u_year_mm  not null,
    fr_ref number(6),
    constraint univ_idx_pk primary key(idx),
    constraint f_u_year_check check (fr_u_year between 1 and 4),
    constraint fr_ref_fk foreign key (fr_ref) references phoneInfo_basic(p_idx) on delete cascade
);








-----------------------
--전화번호 관리 프로그램 
-----------------------
--대리키 : 일련번호 -> p_idx

--기본 정보 테이블 생성 
create table phoneInfo_basic(
    p_idx number(6) constraint p_idx_pk primary key,
    fr_name varchar2(10) constraint fr_name_mm not null,
    fr_phonenumber varchar2(20) constraint fr_phonenumber_mm not null,
    fr_address varchar2(30) default '입력값이 없습니다.',
    fr_email varchar2(20) default '입력값이 없습니다.',
    fr_regdate date default sysdate
);


-- 대학 친구 테이블 생성
create table phoneInfo_univ(
    idx number(6),
    fr_u_major varchar2(20) default 'N'  constraint fr_u_major_mm not null ,
    fr_u_year number(1) default '1'constraint fr_u_year_mm  not null,
    fr_ref number(6),
    constraint univ_idx_pk primary key(idx),
    constraint f_u_year_check check (fr_u_year between 1 and 4),
    constraint fr_ref_fk foreign key (fr_ref) references phoneInfo_basic(p_idx)  on delete cascade
);


-- 회사 친구 테이블 생성
create table phoneInfo_com(
    idx number(6),
    fr_c_company varchar2(20) default 'N' constraint fr_c_company_mm not null,
    fr_ref number(6),
    constraint com_idx_pk primary key(idx),
    constraint fr_ref foreign key (fr_ref) references phoneInfo_basic(p_idx) on delete cascade
);


-- 기본 정보 입력
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber,fr_address,fr_email) values(1,'박지성','010-1234-4578','서울','park@');
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber,fr_address,fr_email) values(2,'손흥민','010-1832-4578','인천','son@');
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber,fr_address,fr_email)values(3,'기성용','010-1234-0862','부산','park@');
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber) 
values(4,'이승우','010-6570-4039');
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber) 
values(5,'차두리','010-0025-1970');
-- 기본 정보 출력
select * from phoneInfo_basic;

--대학친구 정보 입력
insert into phoneInfo_univ values(1,'축구','2',2);
insert into phoneInfo_univ (idx, fr_u_year, fr_ref) values(2,'3',4);
--대학 친구 정보 출력
select * from phoneInfo_univ u, phoneInfo_basic b where u.fr_ref=b.p_idx; 
select * from phoneInfo_univ u, phoneInfo_basic b where u.fr_ref=b.p_idx and b.p_idx=3;  

--회사 친구 정보 입력
insert into phoneInfo_com values(1,'bit',3);
insert into phoneInfo_com values(2,'APPLE',5);
select * from phoneInfo_com c, phoneInfo_basic b where c.fr_ref=b.p_idx;
--회사 친구 정보 출력

--전체 리스트 출력
select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref(+) and b.p_idx=u.fr_ref(+) order by p_idx;
select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref and b.p_idx=u.fr_ref order by p_idx; -- 출력 x


--------------------------------
-- 친구 정보 수정 
---------------------------------
--1. 회사 친구의 정보 변경
select * from phoneInfo_com;

update phoneInfo_com set fr_c_company='NAVER' where idx=1;
update phoneInfo_com set fr_c_company='KAKAO' where fr_ref=(
select p_idx from phoneInfo_basic where p_idx=5
);


--2. 학교 친구의 정보 변경 
select * from phoneInfo_univ;
update phoneInfo_univ set fr_u_major='야구' where idx=2;
update phoneInfo_univ set fr_u_major='수영' where fr_ref=(
select p_idx from phoneInfo_basic where p_idx=2
);

--------------------------------
-- 친구 정보 삭제
---------------------------------
--1. 회사 친구 정보 삭제
select * from phoneInfo_com;
delete from phoneInfo_com where idx=2;
delete from phoneInfo_com where fr_ref in (
    select p_idx from phoneInfo_basic );


--2. 학교 친구 정보 삭제
select * from phoneInfo_univ;
delete from phoneInfo_univ where fr_ref =(
select p_idx from phoneInfo_basic where p_idx=4);
delete from phoneInfo_univ;

delete from phoneInfo_basic where p_idx =(
select fr_ref from phoneInfo_univ where fr_ref=2);

select * from phoneInfo_basic;
update phoneInfo_basic set fr_name='조현우' where fr_name='차두리';

-- 대학친구 정보와 기본정보를 삭제
delete from phoneInfo_basic where p_idx=2;


select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref(+) and b.p_idx=u.fr_ref(+) order by p_idx;




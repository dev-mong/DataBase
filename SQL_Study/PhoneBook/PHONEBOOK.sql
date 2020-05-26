
-----------------------
--전화번호 관리 프로그램 
-----------------------
--대리키 : 일련번호 -> p_idx

--이름, 전화번호, 생일 , 이메일
create table phoneInfo_basic(
    p_idx number(6) constraint p_idx_pk primary key,
    fr_name varchar2(10) constraint fr_name_mm not null,
    fr_phonenumber varchar2(20) constraint fr_phonenumber_mm not null,
    fr_address varchar2(30) default '입력값이 없습니다.',
    fr_email varchar2(20) default '입력값이 없습니다.',
    fr_regdate date default sysdate
);



-- 전공, 학년
create table phoneInfo_univ(
    idx number(6) constraint univ_idx_pk primary key,
    fr_u_major varchar2(20) default 'N'  constraint fr_u_major_pk not null ,
    fr_u_year number(1) not null,
    fr_ref number(7),
    constraint f_u_year_check check (fr_u_year between 1 and 4),
    constraint fr_ref_fk foreign key (fr_ref) references phoneInfo_basic(p_idx)
);


--부서이름, 직금
create table phoneInfo_com(
    idx number(6),
    fr_c_company varchar2(20) default 'N' constraint fr_c_company_mm not null,
    fr_ref number(6),
    constraint com_idx_pk primary key(idx),
    constraint fr_ref foreign key (fr_ref) references phoneInfo_basic(p_idx)
);



--모임이름, 닉네임
create table phoneInfo_club(
    club_idx number(6) constraint club_idx_pk primary key,
    club_clubname varchar2(10) constraint club_clubname_mm not null,
    club_nname varchar2(10) constraint club_nname_mm not null,
    club_fk number(6) constraint club_fk references phoneInfo_basic(p_idx)
);
desc phoneInfo_club;
select * from phoneInfo_club;



select * from tab;
--전화번호부 (contact)
create table contact(
    pidx number(6),
    pname varchar2(10) constraint pname_mm not null,
    phonenumber varchar2(20) constraint phonenumber_mm not null,
    
    address varchar2(30) default '입력값이 없습니다.',
    email varchar2(20) default '입력값이 없습니다.',
    major varchar2(20),
    grade number(1), 
    
    comname varchar2(10),
    dept varchar2(10),
    
    cafename varchar2(10),
    nickname varchar2(10),
    kategorie varchar2(5) not null, 
    
    constraint pidx_pk primary key(pidx),
    constraint kategorie_check check (kategorie in ('univ','com','cafe')),
    constraint grade_check check (grade between 1 and 4)
);







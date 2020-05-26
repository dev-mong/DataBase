
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

-- 기본 정보 입력
insert into phoneInfo_basic values(1,'박지성','010-1234-4578','서울','park@','86/05/05');
insert into phoneInfo_basic values(2,'손흥민','010-1832-4578','인천','son@','89/12/26');
insert into phoneInfo_basic values(3,'기성용','010-1234-0862','부산','park@','90/09/13');
insert into phoneInfo_basic (p_idx, fr_name, fr_phonenumber, fr_regdate) 
values(4,'이승우','010-6570-4039','98/06/07');
select * from phoneInfo_basic;



-- 전공, 학년
create table phoneInfo_univ(
    idx number(6),
    fr_u_major varchar2(20) default 'N'  constraint fr_u_major_mm not null ,
    fr_u_year number(1) default '1'constraint fr_u_year_mm  not null,
    fr_ref number(6),
    constraint univ_idx_pk primary key(idx),
    constraint f_u_year_check check (fr_u_year between 1 and 4),
    constraint fr_ref_fk foreign key (fr_ref) references phoneInfo_basic(p_idx)
);
--대학친구 정보 입력
insert into phoneInfo_univ values(1,'축구','2',2);
insert into phoneInfo_univ (idx, fr_u_year, fr_ref) values(2,'3',4);
select * from phoneInfo_univ u, phoneInfo_basic b where u.fr_ref=b.p_idx; 
select * from phoneInfo_univ u, phoneInfo_basic b where u.fr_ref=b.p_idx and b.p_idx=3;  



-- 부서이름, 직급
create table phoneInfo_com(
    idx number(6),
    fr_c_company varchar2(20) default 'N' constraint fr_c_company_mm not null,
    fr_ref number(6),
    constraint com_idx_pk primary key(idx),
    constraint fr_ref foreign key (fr_ref) references phoneInfo_basic(p_idx)
);
--회사 친구 정보 입력
insert into phoneInfo_com values(1,'bit',3);
select * from phoneInfo_com c, phoneInfo_basic b where c.fr_ref=b.p_idx;

select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref(+) and b.p_idx=u.fr_ref(+) order by p_idx;
select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c where b.p_idx=c.fr_ref and b.p_idx=u.fr_ref order by p_idx; -- 출력 x

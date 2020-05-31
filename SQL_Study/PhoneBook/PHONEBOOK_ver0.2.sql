--테이블 명세서를 보고 DDL 작성

-- 기본 정보
create table phoneInfo_basic(
    idx number(6),
    fr_name varchar2(20) not null,
    fr_phonenumber varchar2(20) not null,
    fr_email varchar2(20),
    fr_address varchar2(20),
    fr_regdate date default sysdate,
    constraint phoneInfo_basic_idx_pk primary key(idx)
);

--대학 친구 정보
create table phoneInfo_univ(
    idx number(6),
    fr_u_major varchar2(20) default 'N' not null,
    fr_u_year number(1) default 1 not null,
    fr_ref number(6),
    constraint univ_idx_pk primary key(idx),
    constraint univ_ref_fk foreign key (fr_ref) references phoneInfo_basic(idx) on delete cascade,
    constraint univ_year_chekck check (fr_u_year between 1 and 5)
);

--회사 친구 정보
create table phoneInfo_com(
    idx number(6),
    fr_c_company varchar(20) default 'N',
    fr_ref number(6),
    constraint com_idx_pk primary key(idx),
    constraint com_ref foreign key (fr_ref) references phoneInfo_basic(idx) on delete cascade
);
desc phoneInfo_com;



------------------------
-- 정보 입력
--------------------------
--학교 친구 정보 입력
desc phoneInfo_basic;
insert into phoneInfo_basic values(1,'박지성','010-2588-9671','park@','서울','88/01/16');
desc phoneInfo_univ;
insert into phoneInfo_univ values(1,'축구','3',1);
delete from phoneInfo_univ where idx=2;

--직장 친구 정보 입력
insert into phoneInfo_basic values(2,'손흥민','010-1234-7592','son@','인천','87/12/07');
insert into phoneInfo_com values(1,'축구회사',2);
select * from phoneInfo_com;
delete from phoneInfo_basic where idx=2;

select * from phoneInfo_basic;
select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c
where b.idx=u.fr_ref(+) and b.idx=c.fr_ref(+);



















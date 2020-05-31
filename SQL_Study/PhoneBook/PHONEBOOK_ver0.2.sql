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
update phoneInfo_univ  set fr_u_major='야구' where idx=1; 
select * from phoneInfo_univ;

--직장 친구 정보 입력
insert into phoneInfo_basic values(2,'손흥민','010-1234-7592','son@','인천','87/12/07');
insert into phoneInfo_com values(1,'축구회사',2);
select * from phoneInfo_com;
delete from phoneInfo_basic where idx=2;

select * from phoneInfo_basic;
select * from phoneInfo_basic b, phoneInfo_univ u, phoneInfo_com c
where b.idx=u.fr_ref(+) and b.idx=c.fr_ref(+);

create sequence pb_basic_idx_seq
start with 0
minvalue 0 --default 값이 1이므로 0으로 변경
;

--2  com 테이블 seq
create sequence pb_com_idx_seq
start with 0
minvalue 0
;

--3. univ 테이블 seq
create sequence pb_univ_idx_seq
start with 0
minvalue 0
;
delete from phoneInfo_basic;
delete from phoneInfo_univ;
delete from phoneInfo_com;


insert into phoneInfo_basic (idx, fr_name, fr_phonenumber,fr_address,fr_email) values(pb_basic_idx_seq.nextval,'박지성','010-1234-4578','서울','park@');
select * from phoneInfo_basic;

select * from phoneInfo_univ;
insert into phoneInfo_univ (idx,fr_u_major, fr_u_year,fr_ref) values(pb_univ_idx_seq.nextval,'축구','2',pb_basic_idx_seq.currval);
select * from phoneInfo_com;
insert into phoneInfo_com (idx,fr_c_company,fr_ref) values(pb_com_idx_seq.nextval,'bit',pb_basic_idx_seq.currval);

















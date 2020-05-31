--전화번호부 (contact)
create table contact(
    pidx number(6),
    pname varchar2(10) constraint pname_mm not null,
    phonenumber varchar2(20) constraint phonenumber_mm not null,
    address varchar2(30) default '입력값이 없습니다.' not null,
    email varchar2(20) default '입력값이 없습니다.' not null,
    
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
drop table contact;

----------------------
--전화번호 입력
-----------------------

--기본정보 입력
insert into contact(pidx, pname, phonenumber,address,email, kategorie)
values(1,'박지성','010-1234-5678','서울','park@','univ');

--이메일과 주소를 입력하지 않을 때 삽입 
insert into contact(pidx, pname, phonenumber, kategorie)
values(5,'박지성','010-1234-5678','univ');

--대학 친구 정보 입력
insert into contact(pidx, pname, phonenumber,address,email, kategorie,major, grade) 
values(2,'손흥민','010-1234-4520','서울','son@','univ','축구','4');

--직장 친구 정보 입력 
insert into contact(pidx, pname, phonenumber,address,email, kategorie, comname, dept)
values(3,'기성용','010-4789-5678','서울','kee@','com','대한민국','공격수');

--동호회 친구 정보 입력
insert into contact(pidx, pname, phonenumber,address,email, kategorie, cafename,nickname)
values(4,'박주호','010-4789-5678','부산','pj@','cafe','soccer','park123');

select * from contact;
truncate table contact; --모든 행 삭제 
desc contact;

--------------------
--전화번호 질의
--------------------
--기본 정보 출력 질의
select pidx,pname,phonenumber,address,email from contact;

--대학 친구 출력 질의
select pname,phonenumber,major, grade from contact where kategorie='univ';

--직장 친구 정보 출력 질의
select pname,phonenumber,comname, dept from contact where kategorie='com';

--동호회 친구 정보 출력 질의
select pname,phonenumber, cafename, nickname from contact where kategorie='cafe';
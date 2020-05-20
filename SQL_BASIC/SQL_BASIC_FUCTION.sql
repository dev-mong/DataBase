-- 함수

-- 1_ 숫자 함수
select mod(10,3)
from dual;

select abs(-10)
from dual;
select abs(sal)
from emp;

select trunc(1282.238273,2) -- 소수점 둘째자리까지만 표현 
from dual;

select trunc(1282.238273,-2) 
from dual;

select trunc(sal,-2)
from  emp;

select round(3.141592,3) -- 소수점 셋째자리 반올림되어 출력
from dual;

select round(315.1592172,-1)
from dual;



--2_ 날짜 함수
select sysdate
from dual;

--TO_CHAR(): date -> varchar2로 형 변환 
select sysdate,to_char(sysdate,'YYYY.MM.DD')
from dual;

select ename,to_char(hiredate,'YYYY.MM.DD DAY') as hiredate
from emp
;

select sysdate, to_char(sysdate, 'HH24:MI:SS')
from dual;

-- 2020.05.20. 11:26
select to_char(sysdate,'YYYY.MM.DD. HH24:MI')
from dual;

--3_ 숫자를 문자로 
-- to_cahr()
-- number -> varchar2
select to_char(12500,'999,999')
from dual;

select to_char(12500,'00,000')
from dual;

select to_char(12500,'99,999,999')
from dual;

select to_char(12500,'L999,999')
from dual;

select to_char(3.14,'999,999.99')
from dual;

select to_char(3.141592,'999.99')
from dual;

-- 예))
select sal, to_char(sal*1000,'L999,999,999'),
to_char(sal*1000*12+nvl(comm,0), 'L999,999,999') AS income
from emp
order by income desc
;


--4_ 숫자를 날짜로 표현
--TO_DATE(원본, 패턴)
--str -> DATE

select to_date('19810220', 'YYYYMMDD')
from dual;

select to_date('1981/02/20', 'YYYY/MM/DD')
from dual;

select ename, hiredate
from emp
where hiredate=TO_DATE('1981/02/20', 'YYYY/MM/DD')
;

select sysdate, to_date('20201225','yyyymmdd'),
trunc(to_date('20201225', 'yyyymmdd')-sysdate)
from dual
;

select sysdate, trunc(sysdate-to_date('19980118','yyyymmdd'))
from dual;



--5_ 숫자 변환
--TO_NUMBER(원본,'페턴')
--str -> number : 산술, 관게 연산을 목적으로 변환 

select to_number('20,000','999,999')-to_number('9,000','999,999')
from dual;



--6 DECODE ()
--if문과 switch문과 유사하다 
select ename, deptno,
decode (deptno, 10, 'ACCOUNTING',
                20, 'RESEARCH',
                30, 'SALES',
                40, 'OPEARATIONS'
)as deptName
from emp;

-- 예제)) 직급에 따라 급여 
-- 직급이 'ANAlYST'인 사원은 5%
-- 'SALESMAN'인 사원은 10%
-- 'MANAGER'인 사원은 15%
--  'CLERK'인 사원은 20%
select ename,sal,
decode(job,
            'ANAlYST', sal+sal*0.05, --sal*1.05
            'SALESMAN',sal+sal*0.1, --sal*1.1
            'MANAGER', sal+sal*0.15, --sal*1.15
            'CLERK', sal+sal*0.2
)as UPSAL
from emp
order by upsal 
;




--6_ CASE 함수
select ename,deptno,
(case
    when deptno=10 then 'ACCOUNTING'
    when deptno=20 then 'REASEARCH'
    when deptno=30 then 'SALES'
    when deptno=40 then 'OPERATING'
    else 'no name'
end) as dname
from emp
order by dname 
;




-------------------------
--그룹함수 ( 집합함수 )
-------------------------
--종류 : SUM, AVG, COUNT, MAX, MIN

--SUM(컬럼이름): 해당 컬럼의 데이터들의 합 반환
--매월 지급되는 급여의 총 합 

select sum(sal) as total
from emp
;

select to_char(sum(sal)*1000,'L999,999,999') as total
from emp
;

select sum(comm)
from emp
;


--avg(컬럼명) : 해당 컬럼의 데이터들의 평균값을 반환
select avg(sal)
from emp
;

select 29025/14 from dual;

select avg(comm)
from emp;

select sum(comm) from emp;
select comm from emp;


-- max, min: 해당 컬럼의 데이터 중에서 최대값, 최소값을 반환 
select max(sal), min(sal), max(comm), min(comm)
from emp
;


-- count(컬럼명 or *) : 행( row )의 개수를 반환 
select count(*) from emp;
select count(comm) 
from emp; --null 값을 제외한 행의 개수를 출력 

select distinct job from emp order by job;
select count(distinct job) 
from emp
order by job
;


-- GROUP BY 절: 특정 컬럼으로 그룹핑을 해준다 
select deptno
from emp
group by deptno
;

--예제) 소속 부서별 평균 급여 구하기
select deptno, round(avg(sal), 0)
from emp
group by deptno
;

select deptno, max(sal), min(sal)
from emp
group by deptno
;

--예제)) 부서별로 사원 수와 커미션을 받는 사원들의 수를 계산
select deptno, count(*), count(comm)
from emp
where comm<> 0
group by deptno
;

--HAVING절
--예제)) 부서별 평균 급여가 2000이상인 부서번호화 부서별 평균 급여를 출력
select deptno, avg(sal),count(*), count(comm), sum(comm)
from emp
group by deptno
having avg(sal)<= 2000
;

-- 직급별, 자표 출력
select job, count(*) as "직급별 인원",
        to_char(sum(sal), 'L999,999') as "직급별 월 총 급여",
        to_char(trunc(avg(sal)), 'L999,999') as "직급별 월 평균 급여",
        nvl(sum(comm), 0) as "직급별 수령 커미션의 총 합",
        to_char(max(sal), 'L999,999') as "직급별 최고 급여 금액"
from emp
group by job
--having count(*)>=3     -- 직급별 인원이 3명이상인 직급
having trunc(avg(sal))>=2000 and count(*)>1-- 직급별 평균이 2000이상이고 인원수가 1명초과
;


select deptno,job
from emp
group by deptno, job
order by job
;















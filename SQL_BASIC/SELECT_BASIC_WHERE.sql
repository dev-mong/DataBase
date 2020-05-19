
-- 특정 데이터를 축출하기 : where 절을 이용
-- select 컬럼명 from 테이블 이름 where 조건 (true/false)

-- 전체 사원 중의 월 급여가 3000 이상인 사원의 이름 리스트 
select ename, sal
from emp
where sal >= 3000;


-- 전체 사원 중 10번 부서의 소속 사원의 이름과 직급을 출력 
select * from emp where deptno=10; -- 10번 부서의 사원 출력
select ename,job
from emp
where deptno = 10;

-- 컬럼의 값과 일치 여부 확인 : =
select *
from emp
where deptno=10
;

-- 문자열 표현 : ' 작은 따옴표로 묶는다 , 문자열은 대소문자 구분한다
select *
from emp
where job='MANAGER'
;
-- 이름으로 검색한다 
select *
from emp
where ename='SCOTT'
;
-- 날짜로 검색한다 
select *
from emp
where hiredate='80-12-17'; -- 날짜를 비교할 때는 작은 따옴표로 묶어서 비교한다 , 대소문자 구분 없음 


-- 논리연산자 : and
-- 10번 부서 소속인 사람들 중에서 => AND 직급이 MANAGER인 사람을 검색하여
-- 사원명, 부서번호, 직급을 출력하려고 한다면
-- 조건 1) 10번 부서 소속 -> deptno=10
-- 조건 2) job='MANAGER'
select ename,deptno,job
from emp
where deptno=10 and job='MANAGER';

-- 논리연산자 : or
-- 10번 부서에 소속된 사원이거나 =>OR
-- 직급이 MANAGER인 사람을 검색하여
-- 사원명, 부서번호, 직급을 출력
select ename,deptno, job
from emp
where deptno=10 or job='MANAGER';

-- 논리연산자 : not
-- 부서번호가 10번이 아닌 사원의
-- 사원이름, 부서번호, 직급을 출력
select *
from emp
where not deptno=10;
--where deptno<>10;
--where deptno !=10;


-- 범위 연산 : 논리연산자의 사용 and
-- 2000~3000 사이의 급여를 받는 사원의 정보 출력
select *
from emp
where sal>=2000 and sal<=3000
;

-- 범위 연산자: between A and B => A이상 B이하
select *
from emp
where sal BETWEEN 2000 and 3000
;
-- ex) 2000을 초과하고 3000미만의 급여를 받는 사원
-- sal > 2000 and sal < 3000
-- sal : 2001~2999
select * 
from emp
--where sal>2000 and sal<3000
where sal BETWEEN 2001 and 2999
;


-- 연도 별 입사자 리스트를 출력 
select *
from emp
--where hiredate > '1982-01-01' 
--where hiredate>='1981-01-01' and hiredate<='1981-12-31'
where hiredate BETWEEN '1981-01-01' and '1981-12-31'
;

-- or 연산을 간소화 시키는 IN 연산자
-- 컬럼 이름 IN(데이터, 데이터, 데이터, ... )
-- 컬럼 = 데이터1 OR 컬럼=데이터2 OR 컬럼=데이터3 OR ...

-- 커미션이 300이거나 500이거나 1400인 사원 이름 
-- 검색하기 위해 IN연산자 사용
select *
from emp
--where comm=300 or comm=500 or comm14000
--where comm in(300,500,1400)
where comm not in(300,500,1400)
;

-- 패턴 검색 : like 연산자
-- 컬럼 이름 like 패턴
-- 패턴 : %, _
-- % -> 0개 이상 문자열이 가능하다
-- 's%' : s로 시작하는 문자열
-- '%s' : s로 끝나는 문자열
-- '%s%' : s를 포함하는 문자열 
-- '%노트북%' : 

-- 이름이 s로 시작하는 사원의 리스트
select *
from emp
--where ename like 'S%'
--where ename like '%IN'
--where ename like '%A%'
where ename like '%AR%'
;


-- 패턴 검색 : _
-- _ -> 1개의 자리수에 어떠한 문자가 와도 가능하다
select *
from emp
--where ename like '_A%'
--where ename like '__A%' --세번째 문자가 A인 경우 _ _ 
--where ename like '%E_' -- 문자 끝에서 두번 째인 경우

-- NOT 패턴
where ename not like '%A%'
;


-- NULL 값 확인을 위한 연산자 : IS NULL, IS NOT NULL
-- 컬럼명 is null : 해당 컬럼의 값이 NULL인 경우
-- 컬럼명 is not null : 해당 컬럼의 값이 NULL이 아닌 경우
select ename,comm
from emp
--where comm is null
where comm is not null
;

-- row의 정렬
-- 오름차순 : 작은쪽에서 큰쪽으로 정렬 ,asc ( 생략 가능, 기본값 )
-- 내림차순 : 큰쪽에서 작은쪽으로 정렬 ,desc( 명시적 사용 )
select ename, sal, hiredate, comm
from emp 
--where empno >0          --생략 가능
--order by ename asc      --문자 오름차순, asc 생략 가능 
--order by ename desc     -- 문자 내림차순
--order by sal asc        -- 숫자 오름차순
--order by sal desc       -- 숫자 내림차순
--order by hiredate asc   -- 날짜 오름차순
--order by hiredate desc  -- 날짜 내림차순 , 최근 날짜 부터 출력
--order by comm asc       -- null 오름차순 ,null 값은 마지막에 출력
order by comm desc        -- null 내림차순      
;

-- 급여가 같은 사람일 경우 이름을 오름차순으로 정렬 
select ename,sal
from emp
order by sal desc, ename asc
;
-- 부서와 이름을 오름차순으로 정렬 
select ename, deptno
from emp
order by deptno, ename
;










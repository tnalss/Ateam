
----------------------------------------------------------------------------------------------


--오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저 생성 
create or replace  PROCEDURE PROC_ATTEND_RESULT
 is
 begin
   UPDATE TBL_ATTEND OLD_TBL
        SET (ATT_CODE)  =  (SELECT CASE       
                                        WHEN ATTEND_DATE is null THEN 'W2' --결근
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '09:00'  and  ATTEND_OFF IS NULL THEN 'W8'  --지각 & 퇴실미체크
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '09:00' and TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W7'  --지각 & 조퇴
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '09:00' THEN 'W3'  --지각
                                        WHEN TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W5' --조퇴
                                        WHEN ATTEND_OFF IS NULL THEN 'W6' --퇴실미체크                                    
                                        ELSE 'W4' END  
                            FROM TBL_ATTEND NOW_TBL 
                            WHERE to_char(attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd')
                             AND OLD_TBL.ATTEND_DATE = NOW_TBL.ATTEND_DATE 
                             AND OLD_TBL.EMP_NO = NOW_TBL.EMP_NO )
    WHERE to_char(old_tbl.attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd') ;
 end;
 /

drop procedure PROC_ATTEND_RESULT;

 --(오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저)실행
exec PROC_ATTEND_RESULT();
 


--트리거 생성해주심
create or replace NONEDITIONABLE TRIGGER "ATEAM"."TRG_ATT_STATE_TEST" 
before 
insert on tbl_attend
    for each row
begin
 select 
 CASE WHEN TO_CHAR(current_date , 'hh24')  >= '09'    THEN 'W3' ELSE 'W4'   
                                   END  into :new.ATT_CODE from dual;
END;





--출근 
INSERT INTO tbl_attend (emp_no,attend_on,att_code)
values(47,to_char(current_date,'yyyy/mm/dd hh24:mi:ss'),'W0');


rollback;

  
--퇴근
UPDATE tbl_attend
SET attend_off = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') , att_code='W1' 
WHERE emp_no= and to_char(attend_date, 'yyyy/mm/dd') =  to_char(current_date, 'yyyy/mm/dd') ;


--로그인한 사원의 지난 날짜 출근 코드와 출근 상태조회 --
select a.*,GET_CODE_VALUE(a.ATT_CODE) ATT_STATE
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
WHERE to_char(attend_date, 'yyyy/mm/dd') <=  to_char(current_date, 'yyyy/mm/dd') 
and a.emp_no =42
order by attend_date desc;




--오늘날짜의 전체 사원 출근 상태 조회  
SELECT a.attend_date,e.emp_no, e.emp_name, c.code_category||c.code_num, c.code_value, a.attend_on, a.attend_off 
from tbl_emp e 
left outer join
            (select * from tbl_attend 
             where to_char(attend_date, 'yyyymmdd') = to_char(current_date, 'yyyymmdd') ) a
            on e.emp_no = a.emp_no 
            left outer join tbl_code c
            on (case when a.attend_date is null then 'W2' else a.att_code end) = c.code_category||c.code_num
order by e.emp_no
;

rollback;



--사원 한 명의 오늘 출근 상태 조회  
select a.* ,GET_CODE_VALUE(a.ATT_CODE) ATT_STATE
from tbl_attend a 
where to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd') and emp_no = 11;  





--오늘 출퇴근한  사원의  업데이트   (애초에 값이 없으면  EX:결근  UPDATE가 안됨)
 UPDATE TBL_ATTEND OLD_TBL                          
        SET (ATT_CODE)  =  (SELECT CASE WHEN ATTEND_DATE is null THEN 'W2' 
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' and attend_off is null  THEN 'W3'    
                                        when attend_off is not null then 'W1'                                    
                                   --ELSE 'W4'
                                   END ATT_STATE
                             FROM TBL_ATTEND NOW_TBL 
                             WHERE OLD_TBL.ATTEND_DATE = NOW_TBL.ATTEND_DATE 
                             AND OLD_TBL.EMP_NO = NOW_TBL.EMP_NO)                             
    WHERE to_char(attend_date, 'yyyy/mm/dd')= to_char(sysdate, 'yyyy/mm/dd') and emp_no = 44 ;







select e.* , a.att_code
from tbl_emp e left join tbl_attend a
on e.emp_no = a.emp_no;

/*
--지난날짜의 사원들 전체의 출퇴근현황 조회  
select t.*,GET_CODE_VALUE(T.ATT_CODE) ATT_STATE 
from (
        SELECT CASE 
                    WHEN ATTEND_ON is null  and attend_off is null THEN 'W2' --결근
                    WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09'  and  ATTEND_OFF IS NULL THEN 'W8'  --지각 & 퇴근미체크
                    WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' and TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W7'  --지각 & 조퇴
                    WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' THEN 'W3'  --지각
                    WHEN TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W5' --조퇴
                    WHEN ATTEND_OFF IS NULL THEN 'W6 ' --퇴근미체크
                    ELSE 'W4' END  Att_state,
                    T.*
        FROM TBL_ATTEND T 
        WHERE to_char(attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd')) t ;
*/



--전체 날짜에 대한 전 사원의 출퇴근 기록
--코드테이블에서 불러온 코드의 상태를 att_state로 엘리야스 주고 사용하기 
SELECT T.* , GET_CODE_VALUE(T.ATT_CODE) ATT_STATE , E.EMP_NAME
FROM TBL_ATTEND T 
FULL OUTER JOIN TBL_EMP E
ON T.EMP_NO = E.EMP_NO ;








--사원의 이미지, 이름, 핸드폰번호, 직급, 지점명, 부서명 조회  & 검색

select e.profile_path,e.emp_name,c.code_value rank_name,c2.code_value branch_name ,c3.code_value department_name
from tbl_emp e left join tbl_org o
on e.emp_no = o.emp_no
left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num
where e.emp_name like '%'||#{keyword}||'%' 
order by  e.emp_name, c.code_value;



--사원의 직급별(이름 ㄱㄴㄷ & 부서 가나다)  조회  & 검색
select e.profile_path,e.emp_name,c.code_value rank_name,c2.code_value branch_name ,c3.code_value department_name
from tbl_emp e left join tbl_org o
on e.emp_no = o.emp_no
left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num
where c.code_value e like '%'||#{keyword}||'%' 
order by  c.code_num, e.emp_name;


--지점별 사원 조회  &검색
select e.profile_path,e.emp_name,c.code_value rank_name,c2.code_value branch_name ,c3.code_value department_name
from tbl_emp e left join tbl_org o
on e.emp_no = o.emp_no
left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num
where c2.code_value like '%'||#{keyword}||'%' 
order by c2.code_value, e.emp_name,c.code_num;

--부서별 가나다 (직급 높->낮 순,이름 ㄱㄴㄷ) 사원 조회
select e.profile_path,e.emp_name,c.code_value rank_name,c2.code_value branch_name ,c3.code_value department_name
from tbl_emp e left join tbl_org o
on e.emp_no = o.emp_no
left join tbl_code c
on o.rank_code = c.code_category||c.code_num
left join tbl_code c2
on o.branch_code  = c2.code_category||c2.code_num left join tbl_code c3
on o.dept_code  = c3.code_category||c3.code_num
where c3.code_value like '%'||#{keyword}||'%' 
order by  c3.code_value,c.code_num, e.emp_name;

insert into tbl_org (emp_no, rank_code,branch_code, dept_code )
values(47,'R5','B0','D2');


select * from tbl_al;
--사원별로 insert해야함 

commit;


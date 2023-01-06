--출퇴근 프로시저
create or replace procedure pro_attend( emp number ) is 
   cnt  number;
begin
    select count(emp_no) into cnt from tbl_attend  
    where to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd') and emp_no = emp ;
    
    if( cnt=0 ) then
        insert into tbl_attend (emp_no,attend_on,att_code,att_state)
        values(emp, sysdate, 'W0','출근');
    else 
        update tbl_attend set attend_off = sysdate, att_code ='W1',att_state='퇴근'
        where emp_no = emp and to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd');        
    end if;
end;
/


--출퇴근처리프로시저 실행
exec pro_attend(4);






--

create or replace  PROCEDURE PROC_ATTEND_RESULT
 is
 begin
   UPDATE TBL_ATTEND OLD_TBL
        SET (ATT_CODE)  =  (SELECT CASE WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09'  and  ATTEND_OFF IS NULL THEN 'W8'  --지각 & 퇴실미체크
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' and TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W7'  --지각 & 조퇴
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' THEN 'W3'  --지각
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
 
 --프로시저실행
exec PROC_ATTEND_RESULT();
/*    

 UPDATE TBL_ATTEND OLD_TBL
--   SET (ATT_STATE)  =  (SELECT CASE WHEN ATTEND_ON is null THEN '결근' 
--                                   WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '9:00' THEN '지각' 
--                                   WHEN ATTEND_OFF IS NULL THEN '퇴근 안찍음?'
--                                   ELSE '정상'
--                                   END ATT_STATE
--                             FROM TBL_ATTEND NOW_TBL 
--                             WHERE OLD_TBL.ATTEND_DATE = NOW_TBL.ATTEND_DATE 
--                             AND OLD_TBL.EMP_NO = NOW_TBL.EMP_NO)
        SET (ATT_CODE)  =  (SELECT CASE WHEN ATTEND_ON is null THEN 'W2' 
                                   WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '9:00' THEN 'W3' 
                                  -- WHEN ATTEND_OFF IS NULL THEN '퇴근 안찍음?'
                                   ELSE 'W4'
                                   END ATT_STATE
                             FROM TBL_ATTEND NOW_TBL 
                             WHERE OLD_TBL.ATTEND_DATE = NOW_TBL.ATTEND_DATE 
                             AND OLD_TBL.EMP_NO = NOW_TBL.EMP_NO)                             
    WHERE to_char(attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd') ;
    
    
delete from tbl_attend where emp_no=4 and to_char(attend_date, 'yyyy/mm/dd') =to_char(sysdate, 'yyyy/mm/dd');
commit;

SELECT CASE WHEN ATTEND_ON is null THEN '결근' 
                                   WHEN TO_CHAR(ATTEND_ON , 'hh24:mm')  > '9:00' THEN '지각' 
                                   WHEN ATTEND_OFF IS NULL THEN '퇴근 안찍음?'
                                   ELSE '정상'
                                   END ATT_STATE
*/






select t.*
from (
SELECT CASE WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09'  and  ATTEND_OFF IS NULL THEN 'W8'  --지각 & 퇴근미체크
            WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' and TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W7'  --지각 & 조퇴
            WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' THEN 'W3'  --지각
            WHEN TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W5' --조퇴
            WHEN ATTEND_OFF IS NULL THEN 'W6 ' --퇴근미체크
            ELSE 'W4' END  ATTend_STATE,
            T.*
FROM TBL_ATTEND T 
WHERE to_char(attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd') ) t
;







--출근 
--insert into tbl_attend (emp_no,attend_on,att_code,att_state)
--values(sysdate,5,to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'),'W0','출근');
--퇴근
--UPDATE tbl_attend
--SET attend_off = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') , att_code='W1', att_state='퇴근'
--WHERE emp_no=1 and to_char(attend_date, 'yyyy/mm/dd') =  to_char(sysdate, 'yyyy/mm/dd') ; 



commit;






-- 사원 한명의 출퇴근 조회 (전체날짜)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off,att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 1;

--사원 한 명의 날짜별 출근 코드와 출근 상태조회 (전체날짜)
select a.emp_no, e.emp_name , a.attend_date, a.att_code, a.att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 1;

--사원 한 명의 오늘 출근 상태 조회 
select * from tbl_attend  
    where to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd') and emp_no = 1;    



--오늘자 전체 사원의 출퇴근상태 조회 
SELECT c.code_category||c.code_num, c.code_value,  e.emp_no, e.emp_name
from tbl_emp e left outer join
            (select * from tbl_attend 
            where to_char(attend_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd') ) a on e.emp_no = a.emp_no 
left outer join tbl_code c
on (case when a.attend_date is null then 'W2' else a.att_code end) = c.code_category||c.code_num
;

--사원 전체의  출퇴근 조회(오늘날짜, 출근 시간 오름차순 기준)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off
from tbl_attend a left join tbl_emp e
on a.emp_no = e.emp_no
order by emp_no , attend_on;


-- 사원들의 출근상태조회( 없는 사원이 결근아니라 휴가 /  연차일경우  ) :  al 테이블에서  가져와야함 
select c.code_value attend_state, a.*,e.emp_name
from tbl_attend a left join tbl_emp e 
on a.emp_no = e.emp_no 
left outer join tbl_code c on a.att_code = c.code_category||c.code_num
order by attend_date desc;







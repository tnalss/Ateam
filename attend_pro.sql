
----------------------------------------------------------------------------------------------


--오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저 생성 
create or replace  PROCEDURE PROC_ATTEND_RESULT
 is
 begin
   UPDATE TBL_ATTEND OLD_TBL
        SET (ATT_CODE)  =  (SELECT CASE       
                                        WHEN ATTEND_ON is null  and attend_off is null THEN 'W2' 
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
 
 
 
 --(오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저)실행
exec PROC_ATTEND_RESULT();
 
-------------------------------------------



rollback;


--출근 
INSERT INTO tbl_attend (attend_date,emp_no,attend_on,att_code)
values(to_char(sysdate,'yyyy/mm/dd'),2,to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'),'W0');


--퇴근
UPDATE tbl_attend
SET attend_off = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') , att_code='W1' 
WHERE emp_no= and to_char(attend_date, 'yyyy/mm/dd') =  to_char(sysdate, 'yyyy/mm/dd') ;

/*
--사원 한 명의 날짜별 출근 코드와 출근 상태조회 
select a.*,GET_CODE_VALUE(a.ATT_CODE) ATT_STATE
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
WHERE to_char(attend_date, 'yyyy/mm/dd') <=  to_char(sysdate, 'yyyy/mm/dd') 
and a.emp_no =2
order by attend_date desc;
*/



COMMIT;

--사원 한 명의 오늘 출근 상태 조회  
select a.* ,GET_CODE_VALUE(a.ATT_CODE) ATT_STATE
from tbl_attend a
where to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd') and emp_no = 2;  



--오늘 출퇴근한  사원의  상태 업데이트 
 UPDATE TBL_ATTEND OLD_TBL                          
        SET (ATT_CODE)  =  (SELECT CASE WHEN ATTEND_ON is null  and attend_off is null THEN 'W2' 
                                        WHEN TO_CHAR(ATTEND_ON , 'hh24')  >= '09' and attend_off is null  THEN 'W3'    
                                        when attend_off is not null then 'W1'                                    
                                   ELSE 'W4'
                                   END ATT_STATE
                             FROM TBL_ATTEND NOW_TBL 
                             WHERE OLD_TBL.ATTEND_DATE = NOW_TBL.ATTEND_DATE 
                             AND OLD_TBL.EMP_NO = NOW_TBL.EMP_NO)                             
    WHERE to_char(attend_date, 'yyyy/mm/dd')= to_char(sysdate, 'yyyy/mm/dd') and emp_no = 3 ;
    
rollback;


--코드값만 출력
SELECT c.code_category||c.code_num, c.code_value,  e.emp_no, e.emp_name
from tbl_emp e 
left outer join
            (select * from tbl_attend 
             where to_char(attend_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd') ) a on e.emp_no = a.emp_no 
left outer join tbl_code c
on (case when a.attend_date is null then 'W2' else a.att_code end) = c.code_category||c.code_num
;



--지난날짜의 사원들 전체의 출퇴근현황 조회  
select t.*,GET_CODE_VALUE(T.ATT_CODE) ATT_STATE , E.EMP_NAME
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
        WHERE to_char(attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd')) t 
FULL OUTER JOIN TBL_EMP E
ON T.EMP_NO = E.EMP_NO
ORDER BY T.EMP_NO
WHERE T.EMP_NO = 1 ;



--전체 날짜에 대한 전 사원의 출퇴근 기록
--코드테이블에서 불러온 코드의 상태를 att_state로 엘리야스 주고 사용하기 
SELECT T.* , GET_CODE_VALUE(T.ATT_CODE) ATT_STATE
FROM TBL_ATTEND T 
order by attend_date desc, emp_no;



-- 전체 날짜의 전체 사원들의 출근상태조회( 없는 사원이 결근아니라 휴가 /  연차일경우  ) :  al 테이블에서  가져와야함 
select c.code_value attend_state, a.*,e.emp_name
from tbl_attend a left join tbl_emp e 
on a.emp_no = e.emp_no 
left outer join tbl_code c on a.att_code = c.code_category||c.code_num
order by attend_date desc;





select a.* , e.emp_name
from tbl_attend a left outer join tbl_emp e
on a.emp_no = e.emp_no;







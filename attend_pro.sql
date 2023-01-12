
----------------------------------------------------------------------------------------------


--오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저 생성 
create or replace PROCEDURE PROC_ATTEND_RESULT
 is
 begin
 MERGE 
 INTO TBL_ATTEND A
USING TBL_EMP E
   ON (to_char(A.attend_date, 'yyyy/mm/dd') < to_char(sysdate, 'yyyy/mm/dd')
                             AND A.EMP_NO = E.EMP_NO)
 WHEN MATCHED THEN
      UPDATE
         SET A.ATT_CODE = ( SELECT CASE       
                                        WHEN TO_CHAR(A.ATTEND_ON , 'hh24:mm')  > '09:00'  and  ATTEND_OFF IS NULL THEN 'W8'  --지각 & 퇴실미체크
                                        WHEN TO_CHAR(A.ATTEND_ON  , 'hh24:mm')  > '09:00' and TO_CHAR(ATTEND_OFF, 'hh24') < '18' THEN 'W7'  --지각 & 조퇴
                                        WHEN TO_CHAR(A.ATTEND_ON  , 'hh24:mm')  > '09:00' THEN 'W3'  --지각
                                        WHEN TO_CHAR(A.ATTEND_OFF , 'hh24') < '18' THEN 'W5' --조퇴
                                        WHEN ATTEND_ON IS NOT NULL AND ATTEND_OFF IS NULL THEN 'W6' --퇴실미체크 
                                        WHEN ATTEND_ON IS NULL AND ATTEND_OFF IS NULL THEN 'W2' --결근
                                        ELSE 'W4' END  --오늘날짜 지나면 출.퇴근은 정상으로 바뀌게 
                            FROM DUAL )
 WHEN NOT MATCHED THEN
      INSERT (A.ATTEND_DATE, A.EMP_NO, A.ATT_CODE)
      VALUES (to_char(sysdate, 'yyyy/mm/dd'), E.EMP_NO, 'W2');

end ;
 /

drop procedure PROC_ATTEND_RESULT;



--트리거 생성해주심
create or replace NONEDITIONABLE TRIGGER "ATEAM"."TRG_ATT_STATE" 
before 
insert on tbl_attend
    for each row
begin
 select 
 CASE WHEN TO_CHAR(current_date , 'hh24')  >= '09'    THEN 'W3' ELSE 'W4'   
                                   END  into :new.ATT_CODE from dual;
END;

 --(오늘 날짜가 지나면 출근,퇴근은 정상 으로 바뀌고 나머지는 코드값과 상태 그대로 두는 프로시저)실행
exec PROC_ATTEND_RESULT;
 




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




rollback;



--사원 한 명의 오늘 출근 상태 조회  
select a.* ,GET_CODE_VALUE(a.ATT_CODE) ATT_STATE
from tbl_attend a 
where to_char(attend_date, 'yyyy/mm/dd') = to_char(sysdate, 'yyyy/mm/dd') and emp_no = 7;  





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



select nvl(a.attend_date , 0) attend_date , e.emp_name
from tbl_attend a
left outer join tbl_emp e
on a.emp_no = e.emp_no
order by a.attend_date;


select * from tbl_attend
where emp_no = 7;










--오늘 날짜에 대한 전 사원의 출퇴근 기록
SELECT a.attend_date,e.emp_no, e.emp_name, c.code_category||c.code_num, c.code_value, a.attend_on, a.attend_off 
from tbl_emp e 
left outer join
            (select * from tbl_attend 
             where to_char(attend_date, 'yyyymmdd') = to_char(current_date, 'yyyymmdd') ) a
            on e.emp_no = a.emp_no                
            left outer join tbl_code c
            on a.att_code = c.code_category||c.code_num
order by e.emp_no
;





commit;


--create or replace procedure pro_attend( emp number ) is 
--    cnt  number;
--begin
--    select count(emp_no) into cnt from tbl_attend  
--    where to_char(attend_date, 'yyyy/mm/dd hh:mi:ss') = to_char(sysdate, 'yyyy/mm/dd hh:mi:ss') and emp_no = emp ;
--    
--    if( cnt=0 ) then
--        
--    else 
--        update tbl_attend set attend_off = sysdate, att_code ='W1',att_state='퇴근'
--        where emp_no = emp and to_char(attend_date, 'yyyy/mm/dd hh:mi:ss') = to_char(sysdate, 'yyyy/mm/dd hh:mi:ss');
--        
--    end if;
--end;
--출퇴근처리
--exec pro_attend(4);

--/


--출근 
insert into tbl_attend (attend_date,emp_no,attend_on,attend_off,att_code,att_state)
values(sysdate,6,to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'),
        to_char(sysdate,'yyyy/mm/dd hh24:mi:ss'),'W0','출근')  ;


--퇴근
UPDATE tbl_attend
SET attend_off = to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') , att_code='W1', att_state='퇴근'
WHERE emp_no=6; 



commit;




--전체목록조회
select a.*,e.emp_name
from tbl_attend a left join tbl_emp e 
on a.emp_no = e.emp_no;


-- 사원 한명의 출퇴근 조회 (오늘날짜)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off,att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 6;

--사원 한명의 출퇴근 조회 (전체날짜)
select a.emp_no, e.emp_name , a.attend_date, a.att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 6;



--사원 전체의  출퇴근 조회(오늘날짜, 출근 시간 오름차순 기준)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off
from tbl_attend a left join tbl_emp e
on a.emp_no = e.emp_no
order by emp_no , attend_on;









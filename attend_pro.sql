create or replace procedure pro_attend( emp number ) is 
    cnt  number;
begin
    select count(emp_no) into cnt from tbl_attend  
    where to_char(attend_date, 'yyyy/mm/dd hh:mi:ss') = to_char(sysdate, 'yyyy/mm/dd hh:mi:ss') and emp_no = emp ;
    
    if( cnt=0 ) then
        insert into tbl_attend(emp_no, attend_on, att_code, att_state)
        values(emp, sysdate, 'W0','출근');
    else 
        update tbl_attend set attend_off = sysdate, att_code ='W1',att_state='퇴근'
        where emp_no = emp and to_char(attend_date, 'yyyy/mm/dd hh:mi:ss') = to_char(sysdate, 'yyyy/mm/dd hh:mi:ss');
        
    end if;
end;
/

--delete from tbl_attend where emp_no= 1 and to_char(attend_date, 'yymmdd')='230105';
commit;
--, to_char(attend_date, 'yyyy/mm/dd hh:mi:ss')aa

--출퇴근처리
exec pro_attend(3);


--전체목록조회
select a.*,e.emp_name
from tbl_attend a left join tbl_emp e 
on a.emp_no = e.emp_no;


-- 사원 한명의 출퇴근 조회 (오늘날짜)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off,att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 1;

--사원 한명의 출퇴근 조회 (전체날짜)
select a.emp_no, e.emp_name , a.attend_date, a.att_state
from tbl_attend a inner join tbl_emp e
on a.emp_no = e.emp_no
and a.emp_no = 1;



--사원 전체의  출퇴근 조회(오늘날짜, 출근 시간 오름차순 기준)
select a.emp_no, e.emp_name ,TO_CHAR(a.ATTEND_ON,'yyyy/mm/dd hh24:mi:ss') ATTEND_ON, TO_CHAR(a.ATTEND_off,'yyyy/mm/dd hh24:mi:ss') ATTEND_off
from tbl_attend a left join tbl_emp e
on a.emp_no = e.emp_no
order by emp_no , attend_on;




--
create or replace procedure pro_attend_state  is
   begin 
    if  att_state = '퇴근'  
    then 
    update tbl_atttend set att_state = '정상'; 
    end if; 
end;



--exec pro attend_state;
--rollback;


--grant create any job to a_team;





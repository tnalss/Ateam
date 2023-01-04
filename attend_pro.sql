create or replace procedure pro_attend( emp number ) is 
    cnt  number;
begin
    select count(emp_no) into cnt from tbl_attend
    where to_char(attend_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd') and emp_no = emp;
    
    if( cnt=0 ) then
        insert into tbl_attend(emp_no, attend_on, att_code)
        values(emp, sysdate, 'W01');
    else 
        update tbl_attend set attend_off = sysdate
        where emp_no = emp and to_char(attend_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd');
    end if;
end;
/

delete from tbl_attend where emp_no= 2 and to_char(attend_date, 'yymmdd')='230104';
commit;



exec pro_attend(2);

select t.*, to_char(attend_date, 'yyyy/mm/dd hh:mi:ss') aa from tbl_attend t;
package attend;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttendVO {

	private String emp_no,attend_date, attend_on, attend_off, att_code, att_state,emp_name,rank_name,branch_name,department_name,
	work_time,workdate,al_approved,al_code,al_reason,al_reg_date,al_state,al_code_value,al_start_date;
	
}

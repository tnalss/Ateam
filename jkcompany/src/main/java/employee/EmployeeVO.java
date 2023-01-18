package employee;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeVO {
	private String emp_no,emp_name,emp_pw,gender, phone, email, address,admin,
	rank_name,branch_name,department_name;
	private int salary;
	private double commission_pct;
	private Date birth,hire_date,end_date;	
	private Date attend_date, attend_on, attend_off;
	private String att_code;
	private String profile_path;
}

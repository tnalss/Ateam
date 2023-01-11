package Attend;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttendVO {
	private int emp_no;
	private String attend_date, attend_on, attend_off, att_code, att_state;
	
}

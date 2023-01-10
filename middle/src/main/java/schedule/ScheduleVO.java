package schedule;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScheduleVO {
	private String sche_no,emp_no,sche_type,sche_title,sche_content;
	private String sche_red,sche_start,sche_end,sche_status,department_name;
}

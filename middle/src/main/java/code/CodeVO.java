package code;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CodeVO {
	private String code_category, code_name,creater,code_value, code_value2,code_comments;
	private int code_num;
	private Date create_date;
}

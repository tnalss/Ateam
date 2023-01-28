package notice;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyVO {
	private int reply_no, board_no, emp_no;
    private String reply_content, reply_status, emp_name,profile_path;
    private Date  reply_create_date;

}

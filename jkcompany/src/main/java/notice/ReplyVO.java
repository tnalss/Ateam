package notice;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyVO {
	private int reply_no, board_no, emp_no;
    private String reply_content, reply_status, reply_create_date, emp_name;

}

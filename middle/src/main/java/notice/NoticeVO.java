package notice;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
    private int board_no, emp_no, board_hits;
    private String board_title, board_content, board_cate;
    private Date write_date;

}

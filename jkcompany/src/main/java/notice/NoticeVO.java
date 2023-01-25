package notice;


import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
    private int board_no, emp_no, board_hits, rnum;
    private String board_title, board_content, board_cate, emp_name;
    private Date write_date;
    private List<NoticeFileVO> fileList;
    
}

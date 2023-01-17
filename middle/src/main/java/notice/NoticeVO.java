package notice;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeVO {
    private int board_no, emp_no, board_hits;
    private String board_title, board_content, board_cate, emp_name;
    private Date write_date;
    private List<NoticeFileVO> fileList;
    
    public void setFileList(List<NoticeFileVO> fileList) {
        this.fileList = fileList;
    }
    

}

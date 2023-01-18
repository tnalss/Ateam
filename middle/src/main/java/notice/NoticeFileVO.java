package notice;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeFileVO {
	private String file_name, file_path;
	private int file_no, board_no;
}

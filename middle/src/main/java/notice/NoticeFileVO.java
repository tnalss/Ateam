package notice;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeFileVO {
	private String file_name, path;
	private int boardfile_code, board_code;
}

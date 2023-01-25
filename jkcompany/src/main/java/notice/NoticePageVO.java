package notice;

import java.util.Date;
import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticePageVO extends PageVO{
	private List<NoticeVO> list;
	 private Date write_date;
}

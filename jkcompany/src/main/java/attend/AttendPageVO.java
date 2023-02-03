package attend;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttendPageVO extends PageVO{
	public List<AttendVO> list;
	
}
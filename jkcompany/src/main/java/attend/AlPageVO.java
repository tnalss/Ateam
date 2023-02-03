package attend;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlPageVO extends PageVO{	
	public List<AttendVO> al_list;
}
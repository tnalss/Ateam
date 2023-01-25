package employee;

import java.util.List;

import common.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeePageVO extends PageVO{
	public List<EmployeeVO> list;
}

package employee;

import java.util.List;

import common.PageVO2;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeePageVO2 extends PageVO2{
	public List<EmployeeVO> list;
}

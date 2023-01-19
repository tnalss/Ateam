package ea;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EaCodeVO {
    private String code_category, code_value, code_value2, creater, code_comments;
    private int code_num;
    private Date create_date;

}
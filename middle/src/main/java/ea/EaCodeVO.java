package ea;

public class EaCodeVO {
    private String code_value, code_comments;
    private int code_num;
    public int getCode_num() {
        return code_num;
    }

    public void setCode_name(int code_num) {
        this.code_num = code_num;
    }

    public String getCode_value() {
        return code_value;
    }

    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }

    public String getCode_comments() {
        return code_comments;
    }

    public void setCode_comments(String code_comments) {
        this.code_comments = code_comments;
    }
}
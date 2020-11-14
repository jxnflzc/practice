package per.jxnflzc.practice.model.enums;

public enum ResponseCode implements CodeEnums{
    SUCCESS("200", "请求成功"),
    PARAM_ERROR("400", "参数错误"),
    FAIL("999", "请求失败");

    private String code;

    private String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

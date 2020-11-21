package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ResponseCode implements CodeEnum {
    SUCCESS("200", "请求成功"),
    PARAM_ERROR("400", "参数错误"),
    NOT_LOGIN("888", "未登录或登录超时"),
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

    public String getDesc() {
        return desc;
    }

    @JsonCreator
    public ResponseCode fromCode(String code) {
        return CodeEnum.fromCode(ResponseCode.class, code);
    }
}

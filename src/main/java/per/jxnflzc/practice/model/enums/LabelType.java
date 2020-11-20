package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum LabelType implements CodeEnum {
    STRING("S", "字符型"),
    NUMBER("N", "数字型");

    private String code;

    private String desc;

    LabelType(String code, String desc) {
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
    public static LabelType fromCode(String code) {
        return CodeEnum.fromCode(LabelType.class, code);
    }
}

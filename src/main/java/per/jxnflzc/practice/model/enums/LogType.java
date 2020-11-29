package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CodeEnumSerializer.class)
public enum LogType implements CodeEnum {
    LABEL("L", "标签管理"),
    NOTICE("N", "通知管理"),
    GROUP("G", "客群管理");

    private String code;

    private String desc;

    LogType(String code, String desc) {
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
    public static LogType fromCode(String code) {
        return CodeEnum.fromCode(LogType.class, code);
    }
}

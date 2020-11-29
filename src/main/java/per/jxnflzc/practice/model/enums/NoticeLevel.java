package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CodeEnumSerializer.class)
public enum NoticeLevel implements CodeEnum {
    NORMAL("normal", "普通"),
    INFO("info", "提示"),
    WARNING("warning", "警告"),
    DANGER("danger", "紧急");

    private String code;

    private String desc;

    NoticeLevel(String code, String desc) {
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
    public static NoticeLevel fromCode(String code) {
        return CodeEnum.fromCode(NoticeLevel.class, code);
    }
}

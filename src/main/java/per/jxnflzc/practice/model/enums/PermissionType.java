package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CodeEnumSerializer.class)
public enum PermissionType implements CodeEnum {
    VISITOR("V", "游客"),
    OTHERS("O", "普通用户"),
    ADMIN("A", "管理员");

    private String code;

    private String desc;

    PermissionType(String code, String desc) {
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
    public static PermissionType fromCode(String code) {
        return CodeEnum.fromCode(PermissionType.class, code);
    }
}

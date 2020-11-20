package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.StringUtils;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface CodeEnum {
    String getCode();

    String getDesc();

    static <E extends CodeEnum> E fromCode(Class<E> type, String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }
        E[] values = type.getEnumConstants();
        for (E e :values) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("非法状态编码：" + code);
    }
}

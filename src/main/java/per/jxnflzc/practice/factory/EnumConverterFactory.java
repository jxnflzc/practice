package per.jxnflzc.practice.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.enums.CodeEnum;

import java.util.HashMap;
import java.util.Map;

public class EnumConverterFactory implements ConverterFactory<String, CodeEnum> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumConverterFactory.class);

    private static final Map<Class,Converter> map = new HashMap<>();

    @Override
    public <T extends CodeEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = map.get(targetType);
        if (converter == null) {
            converter = new StringToCodeEnum<>(targetType);
            map.put(targetType,converter);
        }
        return converter;
    }

    static class StringToCodeEnum<T extends CodeEnum> implements Converter<String, T> {
        private Map<String, T> map = new HashMap<>();

        public StringToCodeEnum(Class<T> targetType) {
            T[] enums = targetType.getEnumConstants();
            for (T t : enums) {
                map.put(t.getCode(), t);
            }
        }

        @Override
        public T convert(String source) {
            T t = map.get(source);
            if (t == null) {
                throw new IllegalArgumentException("非法状态编码：" + source);
            }
            return t;
        }
    }
}

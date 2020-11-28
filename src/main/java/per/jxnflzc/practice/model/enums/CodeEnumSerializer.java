package per.jxnflzc.practice.model.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CodeEnumSerializer extends StdSerializer<CodeEnum> {
    private static final long serialVersionUID = 3546453453546L;

    protected CodeEnumSerializer() {
        super(CodeEnum.class);
    }

    @Override
    public void serialize(CodeEnum codeEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(codeEnum);
        if (codeEnum.getClass().isEnum()) {
            jsonGenerator.writeStringField("name", ((Enum<?>) codeEnum).name());
        }
        jsonGenerator.writeStringField("code", codeEnum.getCode());
        jsonGenerator.writeStringField("desc", codeEnum.getDesc());
        jsonGenerator.writeEndObject();
    }
}

package per.jxnflzc.practice.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

@Component
public class IdUtil {
    private IdGenerator idGenerator;

    public IdUtil() {
        this.idGenerator = new AlternativeJdkIdGenerator();
    }

    public String generatorId() {
        return idGenerator.generateId().toString().replace("-", "");
    }
}

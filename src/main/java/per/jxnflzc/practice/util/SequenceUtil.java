package per.jxnflzc.practice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.dao.SequenceMapper;

@Component
public class SequenceUtil {
    private SequenceMapper sequenceMapper;

    @Autowired
    public void setSequenceMapper(SequenceMapper sequenceMapper) {
        this.sequenceMapper = sequenceMapper;
    }

    public String generatorId(String name) {
        return sequenceMapper.generatorId(name);
    }
}

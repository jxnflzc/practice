package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SequenceMapper {
    String generatorId(@Param("name") String name);
}
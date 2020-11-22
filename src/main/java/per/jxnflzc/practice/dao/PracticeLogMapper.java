package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.PracticeLog;

import java.util.List;

@Mapper
@Component
public interface PracticeLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(PracticeLog record);

    int insertSelective(PracticeLog record);

    PracticeLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(PracticeLog record);

    int updateByPrimaryKey(PracticeLog record);

    List<PracticeLog> queryLogList(@Param("pageable") Pageable pageable,
                                   @Param("keywords") String keywords,
                                   @Param("logType") String logType);

    int queryLogListCount(@Param("keywords") String keywords, @Param("logType") String logType);
}

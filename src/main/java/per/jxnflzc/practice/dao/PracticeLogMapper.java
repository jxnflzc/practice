package per.jxnflzc.practice.dao;

import per.jxnflzc.practice.model.PracticeLog;

public interface PracticeLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(PracticeLog record);

    int insertSelective(PracticeLog record);

    PracticeLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(PracticeLog record);

    int updateByPrimaryKey(PracticeLog record);
}
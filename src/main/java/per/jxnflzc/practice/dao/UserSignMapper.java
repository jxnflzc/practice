package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.UserSign;

@Mapper
@Component
public interface UserSignMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserSign record);

    int insertSelective(UserSign record);

    UserSign selectByPrimaryKey(String userId);

    UserSign select(UserSign record);

    UserSign login(@Param("userSign") UserSign userSign);

    int updateByPrimaryKeySelective(UserSign record);

    int updateByPrimaryKey(UserSign record);
}
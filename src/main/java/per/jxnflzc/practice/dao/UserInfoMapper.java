package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.UserInfo;

@Mapper
@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
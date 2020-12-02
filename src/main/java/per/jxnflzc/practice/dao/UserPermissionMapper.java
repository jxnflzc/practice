package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.UserPermission;

@Mapper
@Component
public interface UserPermissionMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
}

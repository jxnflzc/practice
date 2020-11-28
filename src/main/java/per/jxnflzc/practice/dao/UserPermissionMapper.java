package per.jxnflzc.practice.dao;

import per.jxnflzc.practice.model.UserPermission;

public interface UserPermissionMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
}
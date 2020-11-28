package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.UserGroup;

import java.util.List;

@Mapper
@Component
public interface UserGroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(String groupId);

    List<UserGroup> queryUserGroupList(@Param("pageable") Pageable pageable);

    int queryUserGroupListCount();

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKeyWithBLOBs(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}

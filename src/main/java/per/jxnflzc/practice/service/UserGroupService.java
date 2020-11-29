package per.jxnflzc.practice.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;

public interface UserGroupService {
    ResponseBodyInfo<PageImpl<UserGroup>> queryGroupList(Pageable pageable, String keywords);

    ResponseBodyInfo<UserGroup> queryGroup(String groupId);

    ResponseBodyInfo<String> saveGroup(UserGroup userGroup);

    ResponseBodyInfo<String> deleteGroup(String groupId);
}

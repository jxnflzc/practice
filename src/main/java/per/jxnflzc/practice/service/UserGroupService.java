package per.jxnflzc.practice.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;

public interface UserGroupService {
    ResponseBodyInfo<PageImpl<UserGroup>> queryUserGroupList(Pageable pageable);
}

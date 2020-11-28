package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.jxnflzc.practice.dao.UserGroupMapper;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;
import per.jxnflzc.practice.service.UserGroupService;
import per.jxnflzc.practice.util.SequenceUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    private UserGroupMapper userGroupMapper;

    private SequenceUtil sequenceUtil;

    private static final String USER_GROUP_ID = "user_group.id";

    @Autowired
    public void setUserGroupMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    @Autowired
    public void setSequenceUtil(SequenceUtil sequenceUtil) {
        this.sequenceUtil = sequenceUtil;
    }

    @Override
    public ResponseBodyInfo<PageImpl<UserGroup>> queryUserGroupList(Pageable pageable) {
        int count = userGroupMapper.queryUserGroupListCount();
        List<UserGroup> list = new ArrayList<>();
        if (count > 0) {
            list = userGroupMapper.queryUserGroupList(pageable);
        }
        return ResponseBodyInfo.success(new PageImpl<UserGroup>(list, pageable, count));
    }
}

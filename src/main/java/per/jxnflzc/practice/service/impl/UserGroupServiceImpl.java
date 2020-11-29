package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import per.jxnflzc.practice.dao.UserGroupMapper;
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;
import per.jxnflzc.practice.model.enums.LogType;
import per.jxnflzc.practice.service.PracticeLogService;
import per.jxnflzc.practice.service.UserGroupService;
import per.jxnflzc.practice.util.PracticeUtil;
import per.jxnflzc.practice.util.SequenceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static per.jxnflzc.practice.util.SequenceId.USER_GROUP_ID;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    private UserGroupMapper userGroupMapper;

    private PracticeLogService practiceLogService;

    private SequenceUtil sequenceUtil;

    @Autowired
    public void setUserGroupMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    @Autowired
    public void setPracticeLogService(PracticeLogService practiceLogService) {
        this.practiceLogService = practiceLogService;
    }

    @Autowired
    public void setSequenceUtil(SequenceUtil sequenceUtil) {
        this.sequenceUtil = sequenceUtil;
    }

    @Override
    public ResponseBodyInfo<PageImpl<UserGroup>> queryGroupList(Pageable pageable, String keywords) {
        int count = userGroupMapper.queryGroupListCount(keywords);
        List<UserGroup> list = new ArrayList<>();
        if (count > 0) {
            list = userGroupMapper.queryGroupList(pageable, keywords);
        }
        return ResponseBodyInfo.success(new PageImpl<UserGroup>(list, pageable, count));
    }

    @Override
    public ResponseBodyInfo<UserGroup> queryGroup(String groupId) {
        UserGroup userGroup = userGroupMapper.selectByPrimaryKey(groupId);
        if (userGroup != null) {
            return ResponseBodyInfo.success(userGroup);
        } else {
            return ResponseBodyInfo.error("客群不存在");
        }
    }

    @Override
    public ResponseBodyInfo<String> saveGroup(UserGroup userGroup) {
        String groupId = userGroup.getGroupId();
        boolean first = false;
        if (!StringUtils.hasText(groupId)) {
            first = true;
            groupId = sequenceUtil.generatorId(USER_GROUP_ID);
            userGroup.setGroupId(groupId);
        }

        int count;
        String userId = PracticeUtil.getInstance().getCurrentUserId();
        Date now = new Date();
        userGroup.setUpdatedBy(userId);
        userGroup.setUpdatedTime(now);

        StringBuffer sb = new StringBuffer();

        if (first) {
            sb.append("添加客群：");
            userGroup.setCreatedBy(userId);
            userGroup.setCreatedTime(now);
            count = userGroupMapper.insertSelective(userGroup);
        } else {
            sb.append("修改客群：");
            count = userGroupMapper.updateByPrimaryKeySelective(userGroup);
        }

        sb.append(userGroup.getGroupName());
        PracticeLog log = practiceLogService.generatorLog(LogType.GROUP, sb.toString(), userId);
        practiceLogService.saveLog(log);

        if (count > 0) {
            return ResponseBodyInfo.success("保存成功");
        } else {
            return ResponseBodyInfo.error("保存失败");
        }
    }

    @Override
    public ResponseBodyInfo<String> deleteGroup(String groupId) {
        int count = userGroupMapper.deleteByPrimaryKey(groupId);
        if (count > 0) {
            return ResponseBodyInfo.success("删除成功");
        } else {
            return ResponseBodyInfo.error("删除失败");
        }
    }
}

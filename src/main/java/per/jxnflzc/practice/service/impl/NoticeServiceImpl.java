package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import per.jxnflzc.practice.dao.NoticeMapper;
import per.jxnflzc.practice.dao.UserGroupMapper;
import per.jxnflzc.practice.model.Notice;
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;
import per.jxnflzc.practice.model.enums.LogType;
import per.jxnflzc.practice.service.NoticeService;
import per.jxnflzc.practice.service.PracticeLogService;
import per.jxnflzc.practice.service.UserGroupService;
import per.jxnflzc.practice.util.PracticeUtil;
import per.jxnflzc.practice.util.SequenceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    private NoticeMapper noticeMapper;

    private PracticeLogService practiceLogService;

    private SequenceUtil sequenceUtil;

    @Autowired
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
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
    public ResponseBodyInfo<PageImpl<Notice>> queryNoticeList(Pageable pageable, String keywords) {
        int count = noticeMapper.queryNoticeListCount();
        List<Notice> list = new ArrayList<>();
        if (count > 0) {
            list = noticeMapper.queryNoticeList(pageable);
        }
        return ResponseBodyInfo.success(new PageImpl<Notice>(list, pageable, count));
    }

    @Override
    public ResponseBodyInfo<Notice> queryNotice(String noticeId) {
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        if (notice != null) {
            return ResponseBodyInfo.success(notice);
        } else {
            return ResponseBodyInfo.error("通知不存在");
        }
    }

    @Override
    public ResponseBodyInfo<String> deleteNotice(String noticeId) {
        int count = noticeMapper.deleteByPrimaryKey(noticeId);
        if (count > 0) {
            return ResponseBodyInfo.success("删除成功");
        } else {
            return ResponseBodyInfo.error("删除失败");
        }
    }
}

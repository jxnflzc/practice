package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.jxnflzc.practice.dao.PracticeLogMapper;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.LogType;
import per.jxnflzc.practice.service.PracticeLogService;
import per.jxnflzc.practice.util.IdUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PracticeLogServiceImpl implements PracticeLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeLogServiceImpl.class);

    private PracticeLogMapper practiceLogMapper;

    private IdUtil idUtil;

    @Autowired
    public void setPracticeLog(PracticeLogMapper practiceLogMapper) {
        this.practiceLogMapper = practiceLogMapper;
    }

    @Autowired
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Override
    public ResponseBodyInfo queryLogList(Pageable pageable, String keywords, String logType) {
        int count = practiceLogMapper.queryLogListCount(keywords, logType);
        List<PracticeLog> list = new ArrayList<>();
        if (count > 0) {
            list = practiceLogMapper.queryLogList(pageable, keywords, logType);
        }
        return ResponseBodyInfo.success(new PageImpl<PracticeLog>(list, pageable, count));
    }

    @Override
    public ResponseBodyInfo saveLog(PracticeLog practiceLog) {
        int count = practiceLogMapper.insertSelective(practiceLog);
        if (count > 0) {
            return ResponseBodyInfo.success("保存成功");
        } else {
            return ResponseBodyInfo.error("保存失败");
        }
    }

    @Override
    public PracticeLog generatorLog(LogType logType, String content, String userId) {
        PracticeLog log = new PracticeLog();
        Date now = new Date();
        log.setLogId(idUtil.generatorId());
        log.setLogType(logType);
        log.setLogContent(content);

        log.setCreatedBy(userId);
        log.setCreatedTime(now);
        log.setCreatedTime(now);
        log.setUpdatedBy(userId);
        log.setUpdatedTime(now);
        return log;
    }
}

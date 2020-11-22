package per.jxnflzc.practice.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.LogType;

import java.util.ArrayList;
import java.util.List;

public interface PracticeLogService {
    ResponseBodyInfo saveLog(PracticeLog practiceLog);

    PracticeLog generatorLog(LogType logType, String content, String userId);

    ResponseBodyInfo queryLogList(Pageable pageable, String keywords, String logType);
}

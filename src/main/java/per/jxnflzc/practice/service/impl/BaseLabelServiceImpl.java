package per.jxnflzc.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import per.jxnflzc.practice.dao.BaseLabelMapper;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.LabelTypeCountLabel;
import per.jxnflzc.practice.model.PracticeLog;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.LogType;
import per.jxnflzc.practice.service.BaseLabelService;
import per.jxnflzc.practice.service.PracticeLogService;
import per.jxnflzc.practice.util.IdUtil;
import per.jxnflzc.practice.util.PracticeUtil;
import per.jxnflzc.practice.util.SequenceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BaseLabelServiceImpl implements BaseLabelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseLabelServiceImpl.class);

    private BaseLabelMapper baseLabelMapper;

    private PracticeLogService practiceLogService;

    private IdUtil idUtil;

    private SequenceUtil sequenceUtil;

    private static final String BASE_LABEL_ID = "base_label.id";

    @Autowired
    public void setBaseLabelMapper(BaseLabelMapper baseLabelMapper) {
        this.baseLabelMapper = baseLabelMapper;
    }

    @Autowired
    public void setPracticeLogService(PracticeLogService practiceLogService) {
        this.practiceLogService = practiceLogService;
    }

    @Autowired
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Autowired
    public void setSequenceUtil(SequenceUtil sequenceUtil) {
        this.sequenceUtil = sequenceUtil;
    }

    @Override
    public ResponseBodyInfo<PageImpl<BaseLabel>> queryLabelList(Pageable pageable, String keywords, String labelType) {
        int count = baseLabelMapper.queryLabelListCount(keywords, labelType);
        List<BaseLabel> list = new ArrayList<>();
        if (count > 0) {
            list = baseLabelMapper.queryLabelList(pageable, keywords, labelType);
        }
        return ResponseBodyInfo.success(new PageImpl<BaseLabel>(list, pageable, count));
    }

    @Override
    public ResponseBodyInfo<String> saveLabel(BaseLabel baseLabel) {
        String labelId = baseLabel.getLabelId();
        boolean first = false;
        if (!StringUtils.hasText(labelId)) {
            first = true;
            labelId = sequenceUtil.generatorId(BASE_LABEL_ID);
            baseLabel.setLabelId(labelId);
        }

        int count;
        String userId = PracticeUtil.getInstance().getCurrentUserId();
        Date now = new Date();
        baseLabel.setUpdatedBy(userId);
        baseLabel.setUpdatedTime(now);

        StringBuffer sb = new StringBuffer();

        if (first) {
            sb.append("添加标签：");
            baseLabel.setCreatedBy(userId);
            baseLabel.setCreatedTime(now);
            count = baseLabelMapper.insertSelective(baseLabel);
        } else {
            sb.append("修改标签：");
            count = baseLabelMapper.updateByPrimaryKeySelective(baseLabel);
        }

        sb.append(baseLabel.getLabelName());
        PracticeLog log = practiceLogService.generatorLog(LogType.LABEL, sb.toString(), userId);
        practiceLogService.saveLog(log);

        if (count > 0) {
            return ResponseBodyInfo.success("保存成功");
        } else {
            return ResponseBodyInfo.error("保存失败");
        }
    }

    @Override
    public ResponseBodyInfo<BaseLabel> queryLabel(String labelId) {
        BaseLabel baseLabel = baseLabelMapper.selectByPrimaryKey(labelId);
        if (baseLabel != null) {
            return ResponseBodyInfo.success(baseLabel);
        } else {
            return ResponseBodyInfo.error("标签不存在");
        }
    }

    @Override
    public ResponseBodyInfo<String> deleteLabel(String labelId) {
        int count = baseLabelMapper.deleteByPrimaryKey(labelId);
        if (count > 0) {
            return ResponseBodyInfo.success("删除成功");
        } else {
            return ResponseBodyInfo.error("删除失败");
        }
    }

    @Override
    public ResponseBodyInfo<List<LabelTypeCountLabel>> queryLabelTypeCount() {
        return ResponseBodyInfo.success(baseLabelMapper.queryLabelTypeCount());
    }
}

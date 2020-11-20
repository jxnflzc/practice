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
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.service.BaseLabelService;
import per.jxnflzc.practice.util.IdUtil;
import per.jxnflzc.practice.util.PracticeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BaseLabelServiceImpl implements BaseLabelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseLabelServiceImpl.class);

    private BaseLabelMapper baseLabelMapper;

    private IdUtil idUtil;

    @Autowired
    public void setBaseLabelMapper(BaseLabelMapper baseLabelMapper) {
        this.baseLabelMapper = baseLabelMapper;
    }

    @Autowired
    public void setIdUtil(IdUtil idUtil) {
        this.idUtil = idUtil;
    }

    @Override
    public ResponseBodyInfo queryLabelList(Pageable pageable) {
        int count = baseLabelMapper.queryLabelListCount();
        List<BaseLabel> list = new ArrayList<>();
        if (count > 0) {
            list = baseLabelMapper.queryLabelList(pageable);
        }
        return ResponseBodyInfo.success(new PageImpl<BaseLabel>(list, pageable, count));
    }

    @Override
    public ResponseBodyInfo saveLabel(BaseLabel baseLabel) {
        String labelId = baseLabel.getLabelId();
        boolean first = false;
        if (!StringUtils.hasText(labelId)) {
            first = true;
            labelId = idUtil.generatorId();
            baseLabel.setLabelId(labelId);
        }

        int count;
        String userId = PracticeUtil.getInstance().getCurrentUserId();
        Date now = new Date();
        baseLabel.setUpdatedBy(userId);
        baseLabel.setUpdatedTime(now);
        if (first) {
            baseLabel.setCreatedBy(userId);
            baseLabel.setCreatedTime(now);
            count = baseLabelMapper.insertSelective(baseLabel);
        } else {
            count = baseLabelMapper.updateByPrimaryKeySelective(baseLabel);
        }
        if (count > 0) {
            return ResponseBodyInfo.success("保存成功");
        } else {
            return ResponseBodyInfo.error("保存失败");
        }
    }
}

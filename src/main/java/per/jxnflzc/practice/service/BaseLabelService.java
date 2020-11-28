package per.jxnflzc.practice.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.LabelTypeCountLabel;
import per.jxnflzc.practice.model.ResponseBodyInfo;

import java.util.List;

public interface BaseLabelService {
    ResponseBodyInfo<PageImpl<BaseLabel>> queryLabelList(Pageable pageable, String keywords, String labelType);

    ResponseBodyInfo<BaseLabel> queryLabel(String labelId);

    ResponseBodyInfo<String> deleteLabel(String labelId);

    ResponseBodyInfo<String> saveLabel(BaseLabel baseLabel);

    ResponseBodyInfo<List<LabelTypeCountLabel>> queryLabelTypeCount();
}

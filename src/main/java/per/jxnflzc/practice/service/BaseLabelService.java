package per.jxnflzc.practice.service;

import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.BaseLabel;
import per.jxnflzc.practice.model.ResponseBodyInfo;

public interface BaseLabelService {
    ResponseBodyInfo queryLabelList(Pageable pageable);

    ResponseBodyInfo saveLabel(BaseLabel baseLabel);
}

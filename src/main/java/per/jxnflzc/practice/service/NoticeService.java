package per.jxnflzc.practice.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import per.jxnflzc.practice.model.Notice;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.UserGroup;

public interface NoticeService {
    ResponseBodyInfo<PageImpl<Notice>> queryNoticeList(Pageable pageable, String keywords, String noticeLevel);

    ResponseBodyInfo<Notice> queryNotice(String noticeId);

    ResponseBodyInfo<String> deleteNotice(String noticeId);
}

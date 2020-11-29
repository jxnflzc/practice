package per.jxnflzc.practice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.dao.SequenceMapper;

@Component
public class SequenceId {
    public static final String BASE_LABEL_ID = "base_label.id";

    public static final String USER_GROUP_ID = "user_group.id";

    public static final String NOTICE_ID = "notice.id";
}

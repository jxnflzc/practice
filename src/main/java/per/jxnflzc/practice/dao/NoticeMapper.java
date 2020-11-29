package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.Notice;

import java.util.List;

@Mapper
@Component
public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeId);

    List<Notice> queryNoticeList(@Param("pageable") Pageable pageable,
                                 @Param("keywords") String keywords,
                                 @Param("noticeLevel") String noticeLevel);

    int queryNoticeListCount(@Param("keywords") String keywords,
                             @Param("noticeLevel") String noticeLevel);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}

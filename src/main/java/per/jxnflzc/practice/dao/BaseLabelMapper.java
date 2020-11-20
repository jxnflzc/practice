package per.jxnflzc.practice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import per.jxnflzc.practice.model.BaseLabel;

import java.util.List;

@Mapper
@Component
public interface BaseLabelMapper {
    int deleteByPrimaryKey(String labelId);

    int insert(BaseLabel record);

    int insertSelective(BaseLabel record);

    BaseLabel selectByPrimaryKey(String labelId);

    List<BaseLabel> queryLabelList(@Param("pageable") Pageable pageable);

    int queryLabelListCount();

    int updateByPrimaryKeySelective(BaseLabel record);

    int updateByPrimaryKey(BaseLabel record);
}
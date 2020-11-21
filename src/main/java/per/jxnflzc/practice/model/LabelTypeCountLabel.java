package per.jxnflzc.practice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import per.jxnflzc.practice.model.enums.LabelType;

@ApiModel(description = "标签分类计数")
public class LabelTypeCountLabel extends BaseModel {
    @ApiModelProperty(value = "标签类型")
    private LabelType labelType;

    @ApiModelProperty(value = "标签分类数")
    private Integer count;

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LabelTypeCountLabel{" +
                "labelType=" + labelType +
                ", count=" + count +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}

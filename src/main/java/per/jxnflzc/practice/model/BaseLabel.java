package per.jxnflzc.practice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import per.jxnflzc.practice.model.enums.LabelType;

import java.util.List;

@ApiModel(description = "基础标签")
public class BaseLabel extends BaseModel {
    @ApiModelProperty(value = "标签ID")
    private String labelId;

    @ApiModelProperty(value = "标签名称")
    private String labelName;

    @ApiModelProperty(value = "标签值")
    private String labelValue;

    @ApiModelProperty(value = "标签值", hidden = true)
    private List<String> labelValues;

    @ApiModelProperty(value = "标签类型")
    private LabelType labelType;

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId == null ? null : labelId.trim();
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getLabelValue() {
        return labelValue;
    }

    public List<String> getLabelValues() {
        return labelValues;
    }

    public void setLabelValues(List<String> labelValues) {
        this.labelValues = labelValues;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue == null ? null : labelValue.trim();
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    @Override
    public String toString() {
        return "BaseLabel{" +
                "labelId='" + labelId + '\'' +
                ", labelName='" + labelName + '\'' +
                ", labelValue='" + labelValue + '\'' +
                ", labelValues=" + labelValues +
                ", labelType=" + labelType +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
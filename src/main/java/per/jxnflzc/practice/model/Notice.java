package per.jxnflzc.practice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import per.jxnflzc.practice.model.enums.NoticeLevel;

@ApiModel(description = "公告通知")
public class Notice extends BaseModel{
    @ApiModelProperty(value = "通知ID")
    private String noticeId;

    @ApiModelProperty(value = "通知标题")
    private String noticeTitle;

    @ApiModelProperty(value = "通知级别")
    private NoticeLevel noticeLevel;

    @ApiModelProperty(value = "通知内容")
    private String noticeContent;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public NoticeLevel getNoticeLevel() {
        return noticeLevel;
    }

    public void setNoticeLevel(NoticeLevel noticeLevel) {
        this.noticeLevel = noticeLevel;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId='" + noticeId + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeLevel='" + noticeLevel + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}

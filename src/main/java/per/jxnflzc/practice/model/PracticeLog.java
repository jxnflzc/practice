package per.jxnflzc.practice.model;

import per.jxnflzc.practice.model.enums.LogType;

public class PracticeLog extends BaseModel {
    private String logId;

    private LogType logType;

    private String logContent;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    @Override
    public String toString() {
        return "PracticeLog{" +
                "logId='" + logId + '\'' +
                ", logType='" + logType + '\'' +
                ", logContent='" + logContent + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}

package com.noryar.security.framework.model;

/**
 * Created by leon on 2017/6/4.
 */
public class LogInfoSearchModel extends PageInfoModel {

    private static final long serialVersionUID = 2993911499740250635L;
    private String logType;
    private String logLevel;
    private String logMsgKey;
    private String date;
    /**
     * 限制搜索总数.
     */
    private Integer limit;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogMsgKey() {
        return logMsgKey;
    }

    public void setLogMsgKey(String logMsgKey) {
        this.logMsgKey = logMsgKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-12 16:59
 **/
public class OnlineVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String host;

    private String sessionId;

    private Date startAccessTime;

    private Date lastAccessTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartAccessTime() {
        return startAccessTime;
    }

    public void setStartAccessTime(Date startAccessTime) {
        this.startAccessTime = startAccessTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}

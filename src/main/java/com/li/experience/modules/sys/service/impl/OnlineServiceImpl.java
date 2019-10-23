package com.li.experience.modules.sys.service.impl;

import com.li.experience.modules.sys.domain.OnlineVO;
import com.li.experience.modules.sys.service.OnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-16 15:09
 **/
@Service
public class OnlineServiceImpl implements OnlineService {
    private final SessionDAO sessionDAO;

    @Autowired
    public OnlineServiceImpl(SessionDAO sessionDAO){
        this.sessionDAO = sessionDAO;
    }

    @Override
    public List<OnlineVO> list() {
        List<OnlineVO> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null){
                SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                String username = simplePrincipalCollection.getPrimaryPrincipal().toString();
                OnlineVO onlineVO = new OnlineVO();
                onlineVO.setSessionId(session.getId().toString());
                onlineVO.setUserName(username);
                onlineVO.setHost(session.getHost());
                onlineVO.setStartAccessTime(session.getStartTimestamp());
                onlineVO.setLastAccessTime(session.getLastAccessTime());
                list.add(onlineVO);
            }
        }
        return list;
    }

    @Override
    public void remove(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);
    }
}

package com.hzw.learn.springboot.shirocas.cas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.session.SessionMappingStorage;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyRedisSessionMappingStorage
 * @Description Cas的Session管理，通过redis实现集群session共享
 * @Author houzw
 * @Date 2022/4/10
 **/
public class MyRedisSessionMappingStorage implements SessionMappingStorage {

    /**
     * Maps the ID from the CAS server to the Session.
     */
    private final Map<String,HttpSession> MANAGED_SESSIONS = new HashMap<String,HttpSession>();

    /**
     * Maps the Session ID to the key from the CAS Server.
     */
    private final Map<String,String> ID_TO_SESSION_KEY_MAPPING = new HashMap<String,String>();

    private final Log log = LogFactory.getLog(getClass());

    public synchronized void addSessionById(String mappingId, HttpSession session) {
        ID_TO_SESSION_KEY_MAPPING.put(session.getId(), mappingId);
        MANAGED_SESSIONS.put(mappingId, session);

    }

    public synchronized void removeBySessionById(String sessionId) {
        if (log.isDebugEnabled()) {
            log.debug("Attempting to remove Session=[" + sessionId + "]");
        }

        final String key = ID_TO_SESSION_KEY_MAPPING.get(sessionId);

        if (log.isDebugEnabled()) {
            if (key != null) {
                log.debug("Found mapping for session.  Session Removed.");
            } else {
                log.debug("No mapping for session found.  Ignoring.");
            }
        }
        MANAGED_SESSIONS.remove(key);
        ID_TO_SESSION_KEY_MAPPING.remove(sessionId);
    }

    public synchronized HttpSession removeSessionByMappingId(String mappingId) {
        final HttpSession session = MANAGED_SESSIONS.get(mappingId);

        if (session != null) {
            removeBySessionById(session.getId());
        }

        return session;
    }
}

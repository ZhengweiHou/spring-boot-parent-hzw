package com.hzw.learn.casserver.cas.audit;

import com.github.inspektr.audit.AuditActionContext;
import com.github.inspektr.audit.AuditTrailManager;
import com.google.gson.Gson;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName HzwAuditTrailManager
 * @Description TODO
 * @Author houzw
 * @Date 2022/10/31
 **/
public class HzwAuditTrailManager implements AuditTrailManager {
    public void record(AuditActionContext auditActionContext) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("HZWAUDIT:" + new Gson().toJson(auditActionContext));
    }
}

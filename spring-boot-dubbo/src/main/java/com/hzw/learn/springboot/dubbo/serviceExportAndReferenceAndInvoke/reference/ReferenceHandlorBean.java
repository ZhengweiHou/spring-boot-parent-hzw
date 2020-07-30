package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference;

import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.Hi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component("referenceHandlorBean")
public class ReferenceHandlorBean {
    @Reference(retries=0)
    public Hi hi;
}

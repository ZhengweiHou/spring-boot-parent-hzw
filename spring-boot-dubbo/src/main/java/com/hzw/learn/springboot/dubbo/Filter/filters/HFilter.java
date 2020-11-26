package com.hzw.learn.springboot.dubbo.Filter.filters;

import com.google.gson.Gson;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
@Activate(group = CONSUMER, order = -10000)
public class HFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        System.out.println(invocation);
        System.out.print("=filter=");
        return invoker.invoke(invocation);
    }
}

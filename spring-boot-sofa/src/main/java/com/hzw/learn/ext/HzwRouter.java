package com.hzw.learn.ext;

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.Router;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;

import java.util.List;

/**
 * @ClassName HzwRoute
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
@Extension(value = "hzwRouter",order = 1,override = true)
@AutoActive(consumerSide = true)
public class HzwRouter extends Router {
    public HzwRouter(){
        super();
        System.out.println("======HzwRouter:con");
    }

    @Override
    public List<ProviderInfo> route(SofaRequest request, List<ProviderInfo> providerInfos) {

        System.out.println("==HzwRouter:" + providerInfos.size());
        return providerInfos;
    }
}

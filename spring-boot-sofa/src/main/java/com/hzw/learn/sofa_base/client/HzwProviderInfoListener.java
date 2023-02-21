package com.hzw.learn.sofa_base.client;

import com.alipay.sofa.rpc.client.ProviderGroup;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class HzwProviderInfoListener implements com.alipay.sofa.rpc.listener.ProviderInfoListener {
    Logger logger = LoggerFactory.getLogger(HzwProviderInfoListener.class);

    /**
     * The Ps.
     */
    ConcurrentMap<String, ProviderInfo> ps = new ConcurrentHashMap<String, ProviderInfo>();

    private CountDownLatch countDownLatch;

    /**
     * Sets count down latch.
     *
     * @param countDownLatch the count down latch
     */
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void addProvider(ProviderGroup providerGroup) {
        logger.info("==addProvider: group{} size:{}", providerGroup.getName(), providerGroup.getProviderInfos().size());
        for (ProviderInfo providerInfo : providerGroup.getProviderInfos()) {
            showProviderInfo(providerInfo);
            ps.put(providerInfo.getHost() + ":" + providerInfo.getPort(), providerInfo);
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    @Override
    public void removeProvider(ProviderGroup providerGroup) {
        logger.info("==removeProvider: group{} size:{}", providerGroup.getName(), providerGroup.getProviderInfos().size());
        for (ProviderInfo providerInfo : providerGroup.getProviderInfos()) {
            showProviderInfo(providerInfo);
            ps.remove(providerInfo.getHost() + ":" + providerInfo.getPort());
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    @Override
    public void updateProviders(ProviderGroup providerGroup) {
        logger.info("==updateProviders group{} size:{}", providerGroup.getName(), providerGroup.getProviderInfos().size());
        ps.clear();
        for (ProviderInfo providerInfo : providerGroup.getProviderInfos()) {
            showProviderInfo(providerInfo);
            ps.put(providerInfo.getHost() + ":" + providerInfo.getPort(), providerInfo);
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    @Override
    public void updateAllProviders(List<ProviderGroup> providerGroups) {

        logger.info("==updateAllProviders:" +
                providerGroups.stream().map(pg -> pg.getName() + ":" + pg.getProviderInfos().size() + ",").collect(Collectors.toList()).toString());
        ps.clear();
        for (ProviderGroup providerGroup : providerGroups) {
            for (ProviderInfo providerInfo : providerGroup.getProviderInfos()) {
                showProviderInfo(providerInfo);
                ps.put(providerInfo.getHost() + ":" + providerInfo.getPort(), providerInfo);
            }
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    private void showProviderInfo(ProviderInfo providerInfo){

//        logger.debug(new Gson().toJson(providerInfo));
        logger.debug(
                "{}-{}:{}",
                providerInfo.getStaticAttrs().get("interface"),
                providerInfo.getHost(),
                providerInfo.getPort()
        );
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Map<String, ProviderInfo> getData() {
        return ps;
    }
}

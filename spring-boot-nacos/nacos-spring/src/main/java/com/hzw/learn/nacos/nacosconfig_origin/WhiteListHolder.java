package com.hzw.learn.nacos.nacosconfig_origin;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.utils.StringUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;


/**
 * @ClassName WhiteListHolder
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/11
 **/
public class WhiteListHolder implements EnvironmentAware, InitializingBean {
    Logger log = LoggerFactory.getLogger(getClass());

    private ConfigurableEnvironment environment;

    // 白名单列表
    private final Map<String, List<String>> whites = new ConcurrentHashMap<>();

    private ConfigService configService;
    private Boolean enable;

    // 配置
    private String address; // IP地址
    private String namespace = null;
    private String username;
    private String password;
    private String whiteDataId;
    private String whiteGroup;
    private String whiteConfigType;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkWhiteEnable();
        if (!enable){
            return;
        }

        log.info("enable switch white");

        parseConfig();

        initConfigService();

        initWhites();

        addWhitesListener();
    }

    public List<String> getWhiteList(String key){
        return whites.get(key);
    }

    private void checkWhiteEnable(){
        enable =true;
        String enableTmp = environment.getProperty(enable_switch_white_key);
        if (StringUtils.isBlank(enableTmp) || !enableTmp.toLowerCase().equals("true")){
            log.info("switch white is not enable ");
            enable = false;
        }
    }

    private void parseConfig(){
        String addressInput = environment.getProperty(nacos_address_key);
        Assert.notNull(addressInput,"switch white nacos address is null");

        int idx = addressInput.indexOf(CONTEXT_SEP);
        if (idx > 0) {
            address = addressInput.substring(0, idx);
            namespace = addressInput.substring(idx + 1);
        } else {
            address = addressInput;
        }

        // 若单独配置了nacosNamespace 则以此为准
        String namespaceInput = environment.getProperty(nacos_namespace_key);
        if (namespaceInput != null){
            namespace = namespaceInput;
        }

        if (StringUtils.isBlank(namespace)) {
            namespace = Constants.DEFAULT_NAMESPACE_ID;
        }

        // =nacos username
        username = environment.getProperty(nacos_username_key);

        // =nacos password (base64)
        password = environment.getProperty(nacos_password_key);
        if (!StringUtils.isBlank(password)) {
            password = String.valueOf(Base64Utils.decodeFromString(password));
        }

        // =白名单配置id dataId:group:type dataId必需
        String dataKey = environment.getProperty(white_data_key);
        Assert.notNull(dataKey,"switch white nacos config dataKey is null");
        String[] whiteKey = dataKey.split(":");
        whiteDataId = whiteKey[0];
        if (whiteKey.length>1) {
            whiteGroup = whiteKey[1];
        }
        if (StringUtils.isBlank(whiteGroup)){
            whiteGroup = Constants.DEFAULT_GROUP;
        }
        if (whiteKey.length>2){
            whiteConfigType = whiteKey[2];
        }

    }

    private void updateWhites(String whiteConfigInfo){

        Map whites_tmp = new Gson().fromJson(whiteConfigInfo, whites.getClass());
        whites.clear();
        if (!ObjectUtils.isEmpty(whites_tmp)) {
            whites.putAll(whites_tmp);
        }
        System.out.println("updated whites:" + new Gson().toJson(whites));
    }

    private void initConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, address);
        properties.put(PropertyKeyConst.NAMESPACE,namespace);

        // nacos 服务开启权限验证后需要提供用户名和密码
        if (!StringUtils.isBlank(username)){
            properties.put(PropertyKeyConst.USERNAME,username);
        }
        if (!StringUtils.isBlank(password)){
            properties.put(PropertyKeyConst.PASSWORD,password);
        }

        configService  = NacosFactory.createConfigService(properties);
    }

    private void initWhites() throws NacosException {
        String whiteConfigInfo = configService.getConfig(whiteDataId, whiteGroup, 5000);
        updateWhites(whiteConfigInfo);
    }

    private void addWhitesListener() throws NacosException {
        // 监听
        configService.addListener(whiteDataId, whiteGroup, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String whiteConfigInfo) {
                updateWhites(whiteConfigInfo);
            }
        });
    }

    public final static String enable_switch_white_key = "enableSwitchWhite";
    public final static String nacos_address_key = "nacosAddress";
    public final static String nacos_namespace_key = "nacosNamespace";
    public final static String nacos_username_key = "nacosUsername";
    public final static String nacos_password_key = "nacosPassword";
    // dataId:group:type dataId必填
    public final static String white_data_key = "whiteNacosDataKey";

    public final static String CONTEXT_SEP = "/";


}

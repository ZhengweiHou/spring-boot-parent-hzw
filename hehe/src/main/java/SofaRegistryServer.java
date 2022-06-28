import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

/**
 * @ClassName SofaRegistryServer
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class SofaRegistryServer {

    public static void main(String[] args) {
int i=1;
System.out.println(i++);
        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol("nacos")
                .setAddress("127.0.0.1:8848")
                .setRegister(true);

        System.out.println(i++);
        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt")
                .setPort(12200);
//                .setDaemon(false);

        System.out.println(i++);
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setRegistry(registryConfig)
                .setInterfaceId(HelloService.class.getName())
                .setRef(new HelloServiceImpl())
                .setServer(serverConfig);

        System.out.println(i++);
        providerConfig.export();
        System.out.println(i++);
    }
}

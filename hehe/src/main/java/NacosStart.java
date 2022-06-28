import name.jervyshi.nacos.NacosProcess;
import name.jervyshi.nacos.NacosStarterBuilder;

/**
 * @ClassName NacosStart
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class NacosStart {
    public static void main(String[] args) throws InterruptedException {

        NacosProcess nacos = NacosStarterBuilder.nacosStarter().withNacosVersion("1.0.0").build().start();
        System.out.println("nacos port:" + nacos.getServerPort());

        while (true){
            Thread.sleep(1000);
        }

    }
}

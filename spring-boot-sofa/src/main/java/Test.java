
import com.alibaba.nacos.client.config.impl.ClientWorker;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    static ArrayList al =  new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        ClientWorker
//        Integer times = new Integer(args[0]);
//
//        while (times >0){
//            System.out.println("sleep times:" + times);
//            times --;
//            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//            System.out.println(runtimeMXBean.getName());
//            Thread.sleep(100);
//            al.add(new byte[1 * 1024 * 1024]);
//
//        }

//        String jvmArgs="-Dsystemtag=-hehe -DboltPort=12200";
        String jvmArgs="-Dsystemtag=-hehe -DboltPort=12200 -Dxxxxxx";
//        String jvmArgs="-Dsystemtag=-hehe  -Dxxxxxx";
        Pattern pattern = Pattern.compile(".*boltPort=([0-9]+).*");
        Matcher matcher = pattern.matcher(jvmArgs);
        if(matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(1));
        }
//        System.out.println(matcher.groupCount());



    }
}

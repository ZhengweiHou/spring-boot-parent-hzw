
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;

public class HelloServiceImpl implements HelloService {
    static int times=0;
    ArrayList al =  new ArrayList<Byte[]>();


    @Override
    public String hello(String mesg) {
        System.out.println("Provider接收消息：" + mesg);

        showJVMInfo();
        times++;
//        al.add(new SoftReference(new byte[1 * 1024 * 1024])); // 弱引用占住内存
        return "Provider-" + times;
    }

    @Override
    public String hello2() {
        return "hello too!!";
    }

    private String showJVMInfo(){
        MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = mmb.getHeapMemoryUsage();

        long max = heapMemoryUsage.getMax();
        long init = heapMemoryUsage.getInit();
        long committed = heapMemoryUsage.getCommitted();
        long used = heapMemoryUsage.getUsed();
        System.out.println(String.format("max:%-10s init:%-10s committed:%-10s used%-10s",max>>20,init>>20,committed>>20,used>>20));

        return null;
    }
}

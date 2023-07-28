import com.hzw.grpc.demo.HelloRequest;
import com.hzw.grpc.demo.HelloReply;
import com.hzw.grpc.demo.SimpleGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HzwServiceGrpcTest
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/11
 **/

public class HzwServiceGrpcTest {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();
        SimpleGrpc.SimpleBlockingStub blockingStub = SimpleGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setName("Hzw")
                .build();

        HelloReply response = blockingStub.sayHello(request);

        System.out.println("Server response: " + response.getMessage());
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}

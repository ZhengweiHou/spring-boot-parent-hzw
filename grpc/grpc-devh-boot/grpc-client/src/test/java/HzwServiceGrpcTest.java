import com.hzw.grpc.HelloRequest;
import com.hzw.grpc.HelloResponse;
import com.hzw.grpc.HzwProto;
import com.hzw.grpc.HzwServiceGrpc;
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
        HzwServiceGrpc.HzwServiceBlockingStub blockingStub = HzwServiceGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setName("Hzw")
                .build();

        HelloResponse response = blockingStub.sayHello(request);

        System.out.println("Server response: " + response.getMessage());
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}

package allqueue.grpc_server_demo;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        @Override
        public void hello(
                        HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

                String greeting = new StringBuilder()
                                .append("Hello, ")
                                .append(request.getFirstName())
                                .append(" ")
                                .append(request.getLastName())
                                .toString();
                System.out.println(greeting);

                HelloResponse response = HelloResponse.newBuilder()
                                .setGreeting(greeting)
                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }
}
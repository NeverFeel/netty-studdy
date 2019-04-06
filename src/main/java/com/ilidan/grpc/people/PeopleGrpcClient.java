package com.ilidan.grpc.people;

import com.ilidan.grpc.PeopleRequest;
import com.ilidan.grpc.PeopleResponse;
import com.ilidan.grpc.PeopleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PeopleGrpcClient {

    private final static String ADDRESS = "localhost";
    private final static int PORT = 50051;

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(ADDRESS, PORT)
                .usePlaintext()
                .build();
        PeopleServiceGrpc.PeopleServiceBlockingStub stub= PeopleServiceGrpc.newBlockingStub(channel);

        for(int i = 0; i<5; i++){
            PeopleResponse response = stub.getRealNameByUsername(PeopleRequest.newBuilder().setUsername("zhangsan").build());
            System.out.println(response.getRealName());
            Thread.sleep(1000);
        }
    }

}

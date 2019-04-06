package com.ilidan.grpc.people;

import com.ilidan.grpc.PeopleServiceGrpc;
import com.ilidan.grpc.PeopleStreamRequest;
import com.ilidan.grpc.PeopleStreamResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class PeopleGrpcClient {

    private final static String ADDRESS = "localhost";
    private final static int PORT = 50051;

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(ADDRESS, PORT)
                .usePlaintext()
                .build();
        PeopleServiceGrpc.PeopleServiceBlockingStub stub = PeopleServiceGrpc.newBlockingStub(channel);

//        //普通调用
//        for(int i = 0; i<5; i++){
//            PeopleResponse response = stub.getRealNameByUsername(PeopleRequest.newBuilder().setUsername("zhangsan").build());
//            System.out.println(response.getRealName());
//            Thread.sleep(1000);
//        }

        //服务端输出流
        Iterator<PeopleStreamResponse> iterator = stub.getPeopleByAge(PeopleStreamRequest.newBuilder().setAge(25).build());
        while (iterator.hasNext()) {
            PeopleStreamResponse next = iterator.next();
            System.out.println(next.getName() + "," + next.getAge() + "," + next.getAddress());
        }


    }

}

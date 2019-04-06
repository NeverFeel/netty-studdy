package com.ilidan.grpc.people;

import com.ilidan.grpc.BiRequest;
import com.ilidan.grpc.BiResponse;
import com.ilidan.grpc.PeopleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class PeopleGrpcClient {

    private final static String ADDRESS = "localhost";
    private final static int PORT = 50051;

    public static void main(String[] args) throws InterruptedException {

//        ManagedChannel channel = ManagedChannelBuilder.forAddress(ADDRESS, PORT)
//                .usePlaintext()
//                .build();
//        PeopleServiceGrpc.PeopleServiceBlockingStub stub = PeopleServiceGrpc.newBlockingStub(channel);

//        //普通调用
//        for(int i = 0; i<5; i++){
//            PeopleResponse response = stub.getRealNameByUsername(PeopleRequest.newBuilder().setUsername("zhangsan").build());
//            System.out.println(response.getRealName());
//            Thread.sleep(1000);
//        }

//        //服务端输出流
//        Iterator<PeopleStreamResponse> iterator = stub.getPeopleByAge(PeopleStreamRequest.newBuilder().setAge(25).build());
//        while (iterator.hasNext()) {
//            PeopleStreamResponse next = iterator.next();
//            System.out.println(next.getName() + "," + next.getAge() + "," + next.getAddress());
//        }

        //客户端流
//        ManagedChannel channel = ManagedChannelBuilder.forAddress(ADDRESS, PORT).usePlaintext().build();
//
//        PeopleServiceGrpc.PeopleServiceStub peopleServiceStub = PeopleServiceGrpc.newStub(channel);
//        StreamObserver<PeopleStreamRequest> streamRequestStreamObserver = peopleServiceStub.getPeopleByAges(
//                new StreamObserver<PeopleList>() {
//                    //服务端调用onNext方法时该方法会被调用
//            @Override
//            public void onNext(PeopleList peopleList) {
//                if(peopleList.getPeopleStreamResponseList() == null||peopleList.getPeopleStreamResponseList().size() == 0){
//                    System.out.println("不存在该年龄的数据!");
//                    return;
//                }
//                peopleList.getPeopleStreamResponseList().forEach(item->{
//                    System.out.println(item.getName()+","+item.getAge()+","+item.getAddress());;
//                });
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                System.out.println(throwable.getMessage());
//            }
//
//            //服务端调用onCompleted方法时，该方法会被调用
//            @Override
//            public void onCompleted() {
//                System.out.println("complete!");
//            }
//        });
//
//        streamRequestStreamObserver.onNext(PeopleStreamRequest.newBuilder().setAge(22).build());
//        streamRequestStreamObserver.onNext(PeopleStreamRequest.newBuilder().setAge(23).build());
//        streamRequestStreamObserver.onNext(PeopleStreamRequest.newBuilder().setAge(24).build());
//
//        streamRequestStreamObserver.onCompleted();
//
//        Thread.sleep(10000);
//

        //双向流

        ManagedChannel channel = ManagedChannelBuilder.forAddress(ADDRESS, PORT).usePlaintext().build();
        PeopleServiceGrpc.PeopleServiceStub stub = PeopleServiceGrpc.newStub(channel);

        StreamObserver<BiRequest> streamObserver = stub.biData(new StreamObserver<BiResponse>() {
            @Override
            public void onNext(BiResponse biResponse) {
                System.out.println(biResponse.getResponseInfo());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("complete！");
            }
        });

        for (int i = 0; i < 10; i++) {
            streamObserver.onNext(BiRequest.newBuilder().setRequestInfo("test").build());
            Thread.sleep(1000);
        }
    }

}

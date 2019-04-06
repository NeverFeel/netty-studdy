package com.ilidan.grpc.people;

import com.ilidan.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class PeopleServiceImpl extends PeopleServiceGrpc.PeopleServiceImplBase {

    @Override
    public void getRealNameByUsername(PeopleRequest request,
                                      StreamObserver<PeopleResponse> responseObserver) {
        System.out.println("收到来自客户端的请求，请求参数：" + request.getUsername());
        responseObserver.onNext(PeopleResponse.newBuilder().setRealName("张三1126").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getPeopleByAge(PeopleStreamRequest request, StreamObserver<PeopleStreamResponse> responseObserver) {
        System.out.println("收到来自客户端的请求，请求参数：" + request.getAge());
        responseObserver.onNext(PeopleStreamResponse.newBuilder().setName("张三").setAge(25).setAddress("上海").build());
        responseObserver.onNext(PeopleStreamResponse.newBuilder().setName("李四").setAge(25).setAddress("北京").build());
        responseObserver.onNext(PeopleStreamResponse.newBuilder().setName("王五").setAge(28).setAddress("深圳").build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<PeopleStreamRequest> getPeopleByAges(StreamObserver<PeopleList> responseObserver) {

        //以异步的方式进行调用
        return new StreamObserver<PeopleStreamRequest>() {
            //客户端调用StreamObserver的onNext方法时，该方法会被调用
            @Override
            public void onNext(PeopleStreamRequest peopleStreamRequest) {
                System.out.println("age:" + peopleStreamRequest.getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                //准备数据
                PeopleStreamResponse streamResponse1 = PeopleStreamResponse.newBuilder()
                        .setName("张三")
                        .setAge(25)
                        .setAddress("上海浦东")
                        .build();

                PeopleStreamResponse streamResponse2 = PeopleStreamResponse.newBuilder()
                        .setName("李四")
                        .setAge(26)
                        .setAddress("上海宝山")
                        .build();

                PeopleStreamResponse streamResponse3 = PeopleStreamResponse.newBuilder()
                        .setName("王五")
                        .setAge(23)
                        .setAddress("上海普陀")
                        .build();

                PeopleList peopleList = PeopleList.newBuilder()
                        .addPeopleStreamResponse(streamResponse1)
                        .addPeopleStreamResponse(streamResponse2)
                        .addPeopleStreamResponse(streamResponse3).build();
                responseObserver.onNext(peopleList);
                responseObserver.onCompleted();

            }
        };
    }

    @Override
    public StreamObserver<BiRequest> biData(StreamObserver<BiResponse> responseObserver) {
        return new StreamObserver<BiRequest>() {
            @Override
            public void onNext(BiRequest biRequest) {
                System.out.println("request info："+biRequest.getRequestInfo());
                responseObserver.onNext(BiResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {//客户端调用完成
                //TODO 处理逻辑
                responseObserver.onCompleted();
            }
        };
    }
}

package com.ilidan.grpc.people;

import com.ilidan.grpc.*;
import io.grpc.stub.StreamObserver;

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

}

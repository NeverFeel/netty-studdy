package com.ilidan.grpc.people;

import com.ilidan.grpc.PeopleRequest;
import com.ilidan.grpc.PeopleResponse;
import com.ilidan.grpc.PeopleServiceGrpc;
import io.grpc.stub.StreamObserver;

public class PeopleServiceImpl extends PeopleServiceGrpc.PeopleServiceImplBase {

    @Override
    public void getRealNameByUsername(PeopleRequest request,
                                      StreamObserver<PeopleResponse> responseObserver) {
        System.out.println("收到来自客户端的请求，请求参数：" + request.getUsername());
        responseObserver.onNext(PeopleResponse.newBuilder().setRealName("张三1126").build());
        responseObserver.onCompleted();
    }

}

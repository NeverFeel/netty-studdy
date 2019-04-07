package com.ilidan.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: people.proto")
public final class PeopleServiceGrpc {

  private PeopleServiceGrpc() {}

  public static final String SERVICE_NAME = "com.ilidan.grpc.PeopleService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleRequest,
      com.ilidan.grpc.PeopleResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRealNameByUsername",
      requestType = com.ilidan.grpc.PeopleRequest.class,
      responseType = com.ilidan.grpc.PeopleResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleRequest,
      com.ilidan.grpc.PeopleResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleRequest, com.ilidan.grpc.PeopleResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = PeopleServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (PeopleServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = PeopleServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          PeopleServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod = 
              io.grpc.MethodDescriptor.<com.ilidan.grpc.PeopleRequest, com.ilidan.grpc.PeopleResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.ilidan.grpc.PeopleService", "getRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PeopleServiceMethodDescriptorSupplier("getRealNameByUsername"))
                  .build();
          }
        }
     }
     return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest,
      com.ilidan.grpc.PeopleStreamResponse> getGetPeopleByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPeopleByAge",
      requestType = com.ilidan.grpc.PeopleStreamRequest.class,
      responseType = com.ilidan.grpc.PeopleStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest,
      com.ilidan.grpc.PeopleStreamResponse> getGetPeopleByAgeMethod() {
    io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest, com.ilidan.grpc.PeopleStreamResponse> getGetPeopleByAgeMethod;
    if ((getGetPeopleByAgeMethod = PeopleServiceGrpc.getGetPeopleByAgeMethod) == null) {
      synchronized (PeopleServiceGrpc.class) {
        if ((getGetPeopleByAgeMethod = PeopleServiceGrpc.getGetPeopleByAgeMethod) == null) {
          PeopleServiceGrpc.getGetPeopleByAgeMethod = getGetPeopleByAgeMethod = 
              io.grpc.MethodDescriptor.<com.ilidan.grpc.PeopleStreamRequest, com.ilidan.grpc.PeopleStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.ilidan.grpc.PeopleService", "getPeopleByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleStreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PeopleServiceMethodDescriptorSupplier("getPeopleByAge"))
                  .build();
          }
        }
     }
     return getGetPeopleByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest,
      com.ilidan.grpc.PeopleList> getGetPeopleByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPeopleByAges",
      requestType = com.ilidan.grpc.PeopleStreamRequest.class,
      responseType = com.ilidan.grpc.PeopleList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest,
      com.ilidan.grpc.PeopleList> getGetPeopleByAgesMethod() {
    io.grpc.MethodDescriptor<com.ilidan.grpc.PeopleStreamRequest, com.ilidan.grpc.PeopleList> getGetPeopleByAgesMethod;
    if ((getGetPeopleByAgesMethod = PeopleServiceGrpc.getGetPeopleByAgesMethod) == null) {
      synchronized (PeopleServiceGrpc.class) {
        if ((getGetPeopleByAgesMethod = PeopleServiceGrpc.getGetPeopleByAgesMethod) == null) {
          PeopleServiceGrpc.getGetPeopleByAgesMethod = getGetPeopleByAgesMethod = 
              io.grpc.MethodDescriptor.<com.ilidan.grpc.PeopleStreamRequest, com.ilidan.grpc.PeopleList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.ilidan.grpc.PeopleService", "getPeopleByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.PeopleList.getDefaultInstance()))
                  .setSchemaDescriptor(new PeopleServiceMethodDescriptorSupplier("getPeopleByAges"))
                  .build();
          }
        }
     }
     return getGetPeopleByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ilidan.grpc.BiRequest,
      com.ilidan.grpc.BiResponse> getBiDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "biData",
      requestType = com.ilidan.grpc.BiRequest.class,
      responseType = com.ilidan.grpc.BiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.ilidan.grpc.BiRequest,
      com.ilidan.grpc.BiResponse> getBiDataMethod() {
    io.grpc.MethodDescriptor<com.ilidan.grpc.BiRequest, com.ilidan.grpc.BiResponse> getBiDataMethod;
    if ((getBiDataMethod = PeopleServiceGrpc.getBiDataMethod) == null) {
      synchronized (PeopleServiceGrpc.class) {
        if ((getBiDataMethod = PeopleServiceGrpc.getBiDataMethod) == null) {
          PeopleServiceGrpc.getBiDataMethod = getBiDataMethod = 
              io.grpc.MethodDescriptor.<com.ilidan.grpc.BiRequest, com.ilidan.grpc.BiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.ilidan.grpc.PeopleService", "biData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.BiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ilidan.grpc.BiResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PeopleServiceMethodDescriptorSupplier("biData"))
                  .build();
          }
        }
     }
     return getBiDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PeopleServiceStub newStub(io.grpc.Channel channel) {
    return new PeopleServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PeopleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PeopleServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PeopleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PeopleServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PeopleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(com.ilidan.grpc.PeopleRequest request,
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     */
    public void getPeopleByAge(com.ilidan.grpc.PeopleStreamRequest request,
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleStreamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPeopleByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleStreamRequest> getPeopleByAges(
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetPeopleByAgesMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.ilidan.grpc.BiRequest> biData(
        io.grpc.stub.StreamObserver<com.ilidan.grpc.BiResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.ilidan.grpc.PeopleRequest,
                com.ilidan.grpc.PeopleResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetPeopleByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.ilidan.grpc.PeopleStreamRequest,
                com.ilidan.grpc.PeopleStreamResponse>(
                  this, METHODID_GET_PEOPLE_BY_AGE)))
          .addMethod(
            getGetPeopleByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.ilidan.grpc.PeopleStreamRequest,
                com.ilidan.grpc.PeopleList>(
                  this, METHODID_GET_PEOPLE_BY_AGES)))
          .addMethod(
            getBiDataMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.ilidan.grpc.BiRequest,
                com.ilidan.grpc.BiResponse>(
                  this, METHODID_BI_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class PeopleServiceStub extends io.grpc.stub.AbstractStub<PeopleServiceStub> {
    private PeopleServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeopleServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeopleServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeopleServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(com.ilidan.grpc.PeopleRequest request,
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPeopleByAge(com.ilidan.grpc.PeopleStreamRequest request,
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleStreamResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetPeopleByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleStreamRequest> getPeopleByAges(
        io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetPeopleByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.ilidan.grpc.BiRequest> biData(
        io.grpc.stub.StreamObserver<com.ilidan.grpc.BiResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiDataMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PeopleServiceBlockingStub extends io.grpc.stub.AbstractStub<PeopleServiceBlockingStub> {
    private PeopleServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeopleServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeopleServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeopleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ilidan.grpc.PeopleResponse getRealNameByUsername(com.ilidan.grpc.PeopleRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.ilidan.grpc.PeopleStreamResponse> getPeopleByAge(
        com.ilidan.grpc.PeopleStreamRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetPeopleByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PeopleServiceFutureStub extends io.grpc.stub.AbstractStub<PeopleServiceFutureStub> {
    private PeopleServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PeopleServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PeopleServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PeopleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ilidan.grpc.PeopleResponse> getRealNameByUsername(
        com.ilidan.grpc.PeopleRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_PEOPLE_BY_AGE = 1;
  private static final int METHODID_GET_PEOPLE_BY_AGES = 2;
  private static final int METHODID_BI_DATA = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PeopleServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PeopleServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.ilidan.grpc.PeopleRequest) request,
              (io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleResponse>) responseObserver);
          break;
        case METHODID_GET_PEOPLE_BY_AGE:
          serviceImpl.getPeopleByAge((com.ilidan.grpc.PeopleStreamRequest) request,
              (io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleStreamResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PEOPLE_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getPeopleByAges(
              (io.grpc.stub.StreamObserver<com.ilidan.grpc.PeopleList>) responseObserver);
        case METHODID_BI_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biData(
              (io.grpc.stub.StreamObserver<com.ilidan.grpc.BiResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PeopleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PeopleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ilidan.grpc.People.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PeopleService");
    }
  }

  private static final class PeopleServiceFileDescriptorSupplier
      extends PeopleServiceBaseDescriptorSupplier {
    PeopleServiceFileDescriptorSupplier() {}
  }

  private static final class PeopleServiceMethodDescriptorSupplier
      extends PeopleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PeopleServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PeopleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PeopleServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetPeopleByAgeMethod())
              .addMethod(getGetPeopleByAgesMethod())
              .addMethod(getBiDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}

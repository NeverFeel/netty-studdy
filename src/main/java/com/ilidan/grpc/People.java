// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: people.proto

package com.ilidan.grpc;

public final class People {
  private People() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ilidan_grpc_PeopleRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ilidan_grpc_PeopleRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ilidan_grpc_PeopleResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ilidan_grpc_PeopleResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ilidan_grpc_PeopleStreamRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ilidan_grpc_PeopleStreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ilidan_grpc_PeopleStreamResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ilidan_grpc_PeopleStreamResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ilidan_grpc_PeopleList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ilidan_grpc_PeopleList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\014people.proto\022\017com.ilidan.grpc\"!\n\rPeopl" +
      "eRequest\022\020\n\010username\030\001 \001(\t\"\"\n\016PeopleResp" +
      "onse\022\020\n\010realName\030\001 \001(\t\"\"\n\023PeopleStreamRe" +
      "quest\022\013\n\003age\030\001 \001(\005\"B\n\024PeopleStreamRespon" +
      "se\022\014\n\004name\030\001 \001(\t\022\017\n\007address\030\002 \001(\t\022\013\n\003age" +
      "\030\003 \001(\005\"Q\n\nPeopleList\022C\n\024peopleStreamResp" +
      "onse\030\001 \003(\0132%.com.ilidan.grpc.PeopleStrea" +
      "mResponse2\250\002\n\rPeopleService\022Z\n\025getRealNa" +
      "meByUsername\022\036.com.ilidan.grpc.PeopleReq" +
      "uest\032\037.com.ilidan.grpc.PeopleResponse\"\000\022" +
      "a\n\016getPeopleByAge\022$.com.ilidan.grpc.Peop" +
      "leStreamRequest\032%.com.ilidan.grpc.People" +
      "StreamResponse\"\0000\001\022X\n\017getPeopleByAges\022$." +
      "com.ilidan.grpc.PeopleStreamRequest\032\033.co" +
      "m.ilidan.grpc.PeopleList\"\000(\001B\033\n\017com.ilid" +
      "an.grpcB\006PeopleP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_ilidan_grpc_PeopleRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_ilidan_grpc_PeopleRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ilidan_grpc_PeopleRequest_descriptor,
        new String[] { "Username", });
    internal_static_com_ilidan_grpc_PeopleResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_ilidan_grpc_PeopleResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ilidan_grpc_PeopleResponse_descriptor,
        new String[] { "RealName", });
    internal_static_com_ilidan_grpc_PeopleStreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_ilidan_grpc_PeopleStreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ilidan_grpc_PeopleStreamRequest_descriptor,
        new String[] { "Age", });
    internal_static_com_ilidan_grpc_PeopleStreamResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_ilidan_grpc_PeopleStreamResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ilidan_grpc_PeopleStreamResponse_descriptor,
        new String[] { "Name", "Address", "Age", });
    internal_static_com_ilidan_grpc_PeopleList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_ilidan_grpc_PeopleList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ilidan_grpc_PeopleList_descriptor,
        new String[] { "PeopleStreamResponse", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

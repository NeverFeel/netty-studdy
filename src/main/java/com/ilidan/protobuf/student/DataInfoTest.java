package com.ilidan.protobuf.student;

import com.google.protobuf.InvalidProtocolBufferException;

public class DataInfoTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("ilidan").setAge(27).setAddress("上海").build();

        byte[] student2ByteArray = student.toByteArray();//转化成字节数组

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());

    }

}

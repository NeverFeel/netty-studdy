package com.ilidan.thrift.person.client;

import com.ilidan.thrift.person.Person;
import com.ilidan.thrift.person.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class PersonClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            transport.open();
            Person person = client.findPersonByUsername("ilidan");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());

            System.out.println("---------------------");

            Person person1 = new Person();
            person1.setUsername("lihua");
            person1.setAge(27);
            person1.setAddress("上海浦东新区");
            client.savePerson(person1);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }

    }
}
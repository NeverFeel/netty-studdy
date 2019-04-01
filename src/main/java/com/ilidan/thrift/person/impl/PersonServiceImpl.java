package com.ilidan.thrift.person.impl;

import com.ilidan.thrift.person.DataException;
import com.ilidan.thrift.person.Person;
import com.ilidan.thrift.person.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface{

    @Override
    public Person findPersonByUsername(String username) throws DataException, TException {
        System.out.println("param username:"+username);
        Person person = new Person();
        person.setUsername("ilidan");
        person.setAge(27);
        person.setAddress("上海宝山");
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson method invoked!");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.getAddress());
    }
}

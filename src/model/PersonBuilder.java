package src.model;

import java.util.List;

public class PersonBuilder {
    private String fname;
    private String lanme;
    private Address address;
    private List<Integer> phoneNumber;
    private int age;

    public static PersonBuilder aPerson(){
        return new PersonBuilder();
    }

    public PersonBuilder fname(String fname) {
        this.fname = fname;
        return this;
    }

    public PersonBuilder lanme(String lanme) {
        this.lanme = lanme;
        return this;
    }

    public PersonBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public PersonBuilder phoneNumber(List<Integer> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PersonBuilder age(int age) {
        this.age = age;
        return this;
    }

    public Person build() {
        return new Person(fname, lanme, address, phoneNumber, age);
    }
}
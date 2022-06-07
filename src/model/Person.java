package src.model;

import java.util.List;

public class Person {
    private final String fname;
    private final String lanme;
    private final Address address;
    private final List<Integer> phoneNumber;
    private final int age;

    public Person(String fname, String lanme, Address address, List<Integer> phoneNumber, int age) {
        this.fname = fname;
        this.lanme = lanme;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public String getLanme() {
        return lanme;
    }

    public Address getAddress() {
        return address;
    }

    public List<Integer> getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fname='" + fname + '\'' +
                ", lanme='" + lanme + '\'' +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

}

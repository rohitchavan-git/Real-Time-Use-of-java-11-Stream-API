package src.model;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Person {
    private final String fname;
    private final String lanme;
    private final Address address;
    private final List<Integer> phoneNumber;
    private final int age;

    private final String status;

    public Person(String fname, String lanme, Address address, List<Integer> phoneNumber, int age,String status) {
        this.fname = fname;
        this.lanme = lanme;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.status=status;
    }

    public Optional<Address> getOptionalAddress() {
        return ofNullable(getAddress());
    }

    public String getFname() {
        return fname;
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

    public String getStatus() {
        return status;
    }
}

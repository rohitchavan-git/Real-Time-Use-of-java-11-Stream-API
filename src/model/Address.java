package src.model;

public class Address {

    private final String state;
    private final String city;
    private final Integer pincode;

    public Address(String state, String city, Integer pincode) {
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public Integer getPincode() {
        return pincode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}

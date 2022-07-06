package src.model;

public class ContactWrapper {
    private final Integer phoneNumber;

    ContactWrapper(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }
}

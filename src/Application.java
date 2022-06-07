package src;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Application {

    private static final String OSMANABAD = "Osmanabad";
    private static final String StartingWithO = "O";

    public static void main(String[] args) {
        List<Person> peoples = getPeoples();

        // city wise grp by and find how many peoples in each city start with 'O'

        System.out.println(peoples.stream()
                .map(Person::getAddress)
                .filter(Objects::nonNull)
                .map(Address::getCity)
                .filter(Objects::nonNull)
                .collect(toList()));

    }

    private static void grpByCityCountCityStartWithO(List<Person> peoples) {

        Predicate<Person> personFilter = person11 -> Optional.ofNullable(person11.getAddress())
                .map(Address::getCity)
                .filter(city -> city.startsWith(StartingWithO))
                .isPresent();
        System.out.println(peoples.stream()
                .filter(personFilter)
                .collect(groupingBy(perosn -> perosn.getAddress().getCity(), counting() )));
    }

    private static void getMAXByAge(List<Person> peoples) {
        System.out.println(peoples.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null));
    }

    private static Map<Integer, Set<Integer>> getIntegerSetMap(List<Person> peoples) {
        return peoples.stream()
                .collect(groupingBy(Person::getAge,
                        filtering(p -> p.getPhoneNumber() != null,
                                flatMapping(p -> p.getPhoneNumber().stream(), toSet()))));

    }

    private static void grpByAgeThenGetNameAndFilterNameStartWithR(List<Person> peoples) {
        System.out.println(peoples.stream()
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getFname,
                                filtering(name->name.startsWith("r"),
                                        toList())))));
    }

    private static void groupByAgeFindCount(List<Person> peoples) {
        System.out.println(peoples.stream()
                .collect(groupingBy(Person::getAge,
                        collectingAndThen(counting(), Long::intValue))));
    }

    private static void getListOfCity(List<Person> peoples) {
        Set<String> listOfCity = peoples.stream()
                .map(person -> Optional.ofNullable(person)
                        .flatMap(person1 -> Optional.ofNullable(person1.getAddress()))
                        .map(Address::getCity))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());

        System.out.println(listOfCity);
    }

    private static void getListOfPersonTryLiveInOsmanabad(List<Person> peoples) {
        Predicate<Person> personPredicate = person1 -> Optional.ofNullable(person1)
                .flatMap(nonNullPerson -> Optional.ofNullable(nonNullPerson.getAddress()))
                .map(Address::getCity).filter(OSMANABAD::equals)
                .isPresent();
        System.out.println(peoples.stream()
                .filter(personPredicate)
                .collect(toList()));
    }

    private static List<Person> getPeoples() {
        return   List.of(new Person("rohit", "chavan", new Address("MH", "Osmanabad", 413501), List.of(80586, 805432),25),
                new Person("urmila", "chavan", new Address("MH", "Pune", 411057), List.of(80585, 805431),25),
                new Person("Rahul", "chavan", new Address("MH", "Osmanabad", 54666), List.of(80544, 845432),22),
                new Person("Amol", "gade", new Address("MH", "shirdi", 76549), List.of(9875, 6744),25),
                new Person("ram", "cena", new Address(null, "USA", 413501), List.of(80586, 805432),43),
                new Person("randy", "ortan", null, List.of(80586, 805432),32),
                new Person("rko", "chavan", new Address("MH", null, 413501), null,24));
    }

    private static void streamOnMapCollection() {
        Map<String, Integer> stringIntegerMap = Map.of("Math", 99, "sec",
                98, "java", 96, "c++", 92, "opp", 89);

        sortMapByValue(stringIntegerMap);
    }

    private static void sortMapByValue(Map<String, Integer> stringIntegerMap) {
        System.out.println(getSortedByValue(stringIntegerMap));
    }

    private static LinkedHashMap<String, Integer> getSortedByValue(Map<String, Integer> stringIntegerMap) {
        return stringIntegerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private static class Person {
        private String fname;
        private String lanme;
        private Address address;
        private List<Integer> phoneNumber;
        private int age;

        public Person(String fname, String lanme, Address address, List<Integer> phoneNumber,int age) {
            this.fname = fname;
            this.lanme = lanme;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.age=age;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLanme() {
            return lanme;
        }

        public void setLanme(String lanme) {
            this.lanme = lanme;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<Integer> getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(List<Integer> phoneNumber) {
            this.phoneNumber = phoneNumber;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class Address {
        private String state;
        private String city;
        private Integer pincode;

        public Address(String state, String city, Integer pincode) {
            this.state = state;
            this.city = city;
            this.pincode = pincode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
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

}

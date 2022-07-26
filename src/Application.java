package src;

import src.model.Address;
import src.model.Person;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static src.repo.PersonRepo.getPeoples;

public class Application {

    private static final String OSMANABAD = "OSMANABAD";

    public static void main(String[] args) {

        listOfCountOfPersonAgeGRoup();

    }

    private static void listOfCountOfPersonAgeGRoup() {
        // 18 - 30 younger count   ![ 18 - 30 ] count   ===> [4,3]


        Long countOfYounger = getPeoples()
                .stream()
                .filter(Person::isYounger)
                .count();

        Long  countOfNotYounger= getPeoples()
                .stream()
                .filter(p -> !Person.isYounger(p))
                .count();

        System.out.println(List.of(countOfYounger, countOfNotYounger));


        List<Long> collect = getPeoples().stream()
                .collect(teeing(filtering(Person::isYounger, counting()),
                                filtering(p -> !Person.isYounger(p), counting()),
                        List::of));

        System.out.println(collect);

        List<Integer> integerList = getPeoples().stream()
                .collect(collectingAndThen(partitioningBy(Person::isYounger),
                        mp -> mp.values().stream()
                                .map(List::size)
                                .collect(toList())));

        System.out.println(integerList);
    }

    private static void partitioningByExample() {
        List<Integer> interNumber = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> booleanListMap = interNumber.stream()
                .collect(partitioningBy(i -> i % 2 == 0));

        System.out.println(booleanListMap);
    }

    private static void getListOfPhoneNumberOfYoungerPerson() {
        // 18-30 get list of number
        List<Integer> integers = getPeoples().stream()
                .filter(Person::isYounger)
                .map(Person::getPhoneNumber)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(toList());

        List<Integer> result = getPeoples().stream()
                .filter(Person::isYounger)
                .flatMap(person -> Optional.ofNullable(person.getPhoneNumber()).stream())
                .flatMap(List::stream)
                .collect(toList());


        System.out.println(integers);

        System.out.println(integers);
    }

    private static void getAgeForInputName() {
        // ["rohit","urmila"] -> [getPeoples]===>age   [rohit: 23 , urmila: 25]

        List<String> input = List.of("rohit", "urmila");

        Map<String, List<Integer>> listMap = getPeoples().stream()
                .filter(person -> input.contains(person.getFname()))
                .collect(groupingBy(Person::getFname, mapping(Person::getAge, toList())));

        System.out.println(listMap);

        System.out.println(listMap.entrySet().stream()
                                            .map(Application::getStringIntegerHashMap)
                                            .collect(toList()));
    }

    private static HashMap<String, Integer> getStringIntegerHashMap(Map.Entry<String, List<Integer>> mp) {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(mp.getKey(), mp.getValue().get(0));
        return stringIntegerHashMap;
    }

    private static void flatMapExamples() {
        List<Integer> integerList = getPeoples().stream()
                                                .flatMap(person -> person.getPhoneNumber().stream())
                                                .collect(toList());
        List<Integer> integerList1 = getPeoples().stream()
                                                .map(Person::getPhoneNumber)
                                                .flatMap(List::stream)
                                                .collect(toList());


        List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> transform = numberList.stream()
                                            .map(i -> i * i)
                                            .collect(toList());

        List<Integer> transformationUsingFlatMap = integerList.stream()
                                                                .map(i -> List.of(i - 1, i + 1))
                                                                .flatMap(List::stream)
                                                                .collect(toList());
    }

    private static void preSEssion(List<Person> peoples) {
        // collector => toList,toSet,toMap(), grouppingby()  , joining , mapping , flatmapping
        // ,filterring , collectingAndThen
        //counting ,teeing


        Map<Integer, List<Person>> collect = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge));

        Map<Integer, Set<Person>> collect1 = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge, toSet()));

        Map<Integer, Long> collect3 = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge, counting()));


        Map<Integer, Integer> collect4 = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge,
                        collectingAndThen(counting(),
                                    Long::intValue)));

        Map<Integer, List<String>> collect2 = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge,
                        mapping(Person::getFname, toList())));

        Map<Integer, List<String>> collect5 = peoples.stream()
                .filter(p -> p.getAge() != 0)
                .collect(groupingBy(Person::getAge,
                            mapping(Person::getFname,
                                filtering("R"::startsWith,
                                        toList()))));


        List<Address> addresses = peoples.stream()
                .map(Person::getAddress)
                .collect(collectingAndThen(toList(), p -> p.stream()
                        .filter(p1 -> p1.getCity().equalsIgnoreCase("O"))
                        .collect(toList())));


        Map<Boolean, List<Person>> collect6 = peoples.stream()
                .collect(partitioningBy(p -> p.getAge() % 2 == 0));


        // types of common collectors
        // groupBy(Function f, Collector t)
        // mapping(function , collector)
        //flatMApping(function , collector)
        //filtering(function , collector)
        // collectingAndThen(Collector , Function)
        // teeing(collector , collector , Bifunction)
    }

    private static void getALlNumbers(List<Person> peoples) {
        List<Integer> integers = peoples.stream()
                .map(Person::getPhoneNumber)
                .flatMap(List::stream)
                .collect(toList());
    }

    private static List<Person> getCityOSMANA(List<Person> peoples) {
        Predicate<Person> checkNullOrNot=
                        person -> Optional.ofNullable(person.getAddress())
                                            .map(Address::getCity)
                                            .filter(OSMANABAD::equalsIgnoreCase)
                                          .isPresent();


        return peoples.stream()
                .filter(checkNullOrNot)
                .collect(toList());
    }

    private static List<String> getCity(List<Person> peoples) {
        // get city

        return peoples.stream()
                .map(Person::getAddress)
                .filter(Objects::nonNull)
                .map(Address::getCity)
                .collect(toList());
    }


}

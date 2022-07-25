package src;

import src.model.Address;
import src.model.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static src.repo.PersonRepo.getPeoples;

public class Application {

    private static final String OSMANABAD = "OSMANABAD";
    private static final String STARTWITHR = "R";

    public static void main(String[] args) {
        List<Person> peoples = getPeoples();

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
                                filtering(STARTWITHR::startsWith,
                                        toList()))));


        List<Address> addresses = peoples.stream()
                .map(Person::getAddress)
                .collect(collectingAndThen(toList(), p -> p.stream()
                        .filter(p1 -> p1.getCity().equalsIgnoreCase("O"))
                        .collect(toList())));


        Map<Boolean, List<Person>> collect6 = peoples.stream()
                .collect(partitioningBy(p -> p.getAge() % 2 == 0));




        // groupBy(Function f, Collector t)
        //mapping(function , collector)
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


        List<Person> personList = peoples.stream()
                .filter(checkNullOrNot)
                .collect(toList());
        return personList;
    }

    private static List<String> getCity(List<Person> peoples) {
        // get city

        List<String> collect = peoples.stream()
                .map(Person::getAddress)
                .filter(Objects::nonNull)
                .map(Address::getCity)
                .collect(toList());
        return collect;
    }


}

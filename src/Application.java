package src;

import src.model.Address;
import src.model.Person;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static src.repo.PersonRepo.getPeoples;

public class Application {

    private static final String OSMANABAD = "Osmanabad";
    private static final String STARTING_WITH_R = "r";

    public static void main(String[] args) {
        List<Person> peoples = getPeoples();

        // city wise grp by and find how many peoples in each city start with 'O'

        List<String> listOfCity  = peoples.stream()
                                            .map(Person::getAddress)
                                            .filter(Objects::nonNull)
                                            .map(Address::getCity)
                                            .filter(Objects::nonNull)
                                        .collect(toList());

        System.out.println(listOfCity);

    }

    private static Person getMAXByAge(List<Person> peoples) {
        return peoples.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null);
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
                                filtering(name->name.startsWith(STARTING_WITH_R),
                                        toList())))));
    }

    private static Map<Integer, Integer> groupByAgeFindCount(List<Person> peoples) {
        return peoples.stream()
                .collect(groupingBy(Person::getAge,
                        collectingAndThen(counting(), Long::intValue)));
    }

    private static Set<String> getListOfCity(List<Person> peoples) {

        return peoples.stream()
                            .map(person -> ofNullable(person)
                                    .flatMap(Person::getOptionalAddress )
                                    .map(Address::getCity))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                      .collect(toSet());
    }

    private static List<Person> getListOfPersonTryLiveInOsmanabad(List<Person> peoples) {
        Predicate<Person> personPredicate = person1 -> ofNullable(person1)
                .flatMap(Person::getOptionalAddress)
                .map(Address::getCity)
                .filter(OSMANABAD::equals).isPresent();
        return peoples.stream()
                .filter(personPredicate)
                .collect(toList());
    }

    private static LinkedHashMap<String, Integer> streamOnMapCollection() {

        return sortMapByValue(Map.of("Math", 99, "sec",
                98, "java", 96, "c++", 92, "opp", 89));
    }

    private static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> stringIntegerMap) {
        return getSortedByValue(stringIntegerMap);
    }

    private static LinkedHashMap<String, Integer> getSortedByValue(Map<String, Integer> stringIntegerMap) {
        return stringIntegerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private Map<String, Long> grpByCityGetCount(List<Person> peoples, Predicate<Person> isCity) {

        return peoples.stream()
                        .filter(isCity)
                        .collect(groupingBy(perosn -> perosn.getAddress().getCity()
                            , counting()));
    }
}

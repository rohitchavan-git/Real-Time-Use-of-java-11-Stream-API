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
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;
import static src.repo.PersonRepo.getPeoples;

public class Application {

    private static final String OSMANABAD = "Osmanabad";
    private static final String STARTING_WITH_R = "r";

    public static void main(String[] args) {
        List<Person> peoples = getPeoples();

        // max person by age per first name character

        Function<Person, Character> personCharacterFunction =
                person1 -> ofNullable(person1.getFname())
                                .map(name -> name.charAt(0))
                                .orElse(null);

        Comparator<Person> compareByAge = Comparator.comparing(Person::getAge);

        Map<Character, Person> characterOptionalMap = peoples.stream()
                .collect(groupingBy(personCharacterFunction,
                        collectingAndThen(reducing(BinaryOperator.maxBy(compareByAge)),
                                person -> person.orElse(null))));
        System.out.println(characterOptionalMap);


    }

    private static List<String> getListOfNonNullCity(List<Person> peoples) {
        return peoples.stream()
                    .map(Person::getAddress)
                    .map(Address::getCity)
                    .filter(Objects::nonNull)
                    .collect(toList());
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

package src;

import src.model.Address;
import src.model.Person;
import src.service.PersonService;
import src.service.StudentDashboard;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;
import static src.repo.PersonRepo.getPeoples;

public class Application {

    private static final String STARWITHR = "r";
    private static final String OSMANABAD = "Osmanabad";

    public static void main(String[] args) {

        PersonService personService = new PersonService();

        personService.filterDashboard(StudentDashboard.LIB,"rohit");



    }

    private static void testStream() {
        List<Person> peoples = getPeoples();

        // max person by age per first name character

        Function<Person, Character> personCharacterFunction =
                person1 -> ofNullable(person1.getFname())
                                .map(name -> name.charAt(0))
                                .orElse(null);


        Function<Person,String> getCity= person ->
                    Optional.ofNullable(person)
                            .flatMap(Person::getOptionalAddress)
                            .map(Address::getCity)
                            .orElse(null);


        List<String> collect = peoples.stream()
                .map(p -> ofNullable(p.getAddress())
                        .map(Address::getCity))
                .flatMap(Optional::stream)
                .collect(toList());

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
                                filtering(name->name.startsWith(STARWITHR),
                                        toList())))));
    }

    private static Map<Integer, Integer> groupByAgeFindCount(List<Person> peoples) {
        return peoples.stream()
                .collect(groupingBy(Person::getAge,
                        collectingAndThen(counting(), Long::intValue)));
    }

    private static Set<String> getListOfCity(List<Person> peoples) {

        Function<Person, Optional<String>> getOptionalPerson = person1 -> ofNullable(person1)
                .flatMap(Person::getOptionalAddress)
                .map(Address::getCity);
        return peoples.stream()
                            .map(getOptionalPerson)
                            .flatMap(Optional::stream)
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

package src.service;

import src.model.Person;
import src.repo.PersonRepo;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PersonService {

    public List<Person> filerCOMPLibDashboard(String names) {

        System.out.println("in the Computer lib dashboard");
        List<Person> peoples = PersonRepo.getPeoples();
        return peoples.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(names))
                .collect(Collectors.toList());
    }

    public List<Person> filterFOODDashboard(String names) {

        System.out.println("in the food dashboard ");
        List<Person> peoples = PersonRepo.getPeoples();
        return peoples.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(names))
                .collect(Collectors.toList());
    }

    public List<Person> filterLIBDashboard(String names) {
        System.out.println("in the Lib dashboard");
        List<Person> peoples = PersonRepo.getPeoples();
        return peoples.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(names))
                .collect(Collectors.toList());
    }

    public List<Person> filterDashboard(StudentDashboard studentDashboard, String name) {

        Objects.requireNonNull(studentDashboard);

        return studentDashboard.filterAlgo.apply(this, name);


    }
}

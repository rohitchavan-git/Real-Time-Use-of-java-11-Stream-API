package src.service;

import src.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class PersonService {

    List<Person> people = new ArrayList<>();

    public List<Person> filterDashboard(StudentDashboard studentDashboard , String filterValue){
        requireNonNull(studentDashboard, "Invalid Input");
        requireNonNull(filterValue,"Invalid input");
        return studentDashboard.filterAlgo.apply(this, filterValue);

    }


    public List<Person> filterLIBDashboard(String s) {
        return people.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(s))
                .collect(Collectors.toList());
    }

    public List<Person> filterFOODDashboard(String s) {
        return people.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(s))
                .collect(Collectors.toList());
    }

    public List<Person> filerCOMPLibDashboard(String s) {
        return people.stream()
                .filter(person -> person.getFname().equalsIgnoreCase(s))
                .collect(Collectors.toList());
    }
}

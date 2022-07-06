package src.service;

import src.model.Person;

import java.util.List;
import java.util.function.BiFunction;

public enum StudentDashboard {

    LIB(PersonService::filterLIBDashboard),
    FOOD(PersonService::filterFOODDashboard),
    COMP_LIB(PersonService::filerCOMPLibDashboard);

    public final BiFunction<PersonService, String, List<Person>> filterAlgo;

    StudentDashboard(BiFunction<PersonService, String, List<Person>> filterAlgo) {
        this.filterAlgo = filterAlgo;
    }
}

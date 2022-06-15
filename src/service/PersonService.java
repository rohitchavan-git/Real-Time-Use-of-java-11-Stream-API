package src.service;

import src.model.Person;
import src.util.DashboardFilter;
import src.util.EnumUtils;
import src.util.EnumUtils.DashboardType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class PersonService {

    List<Person> people = new ArrayList<>();

    public List<Person> filterDashboard(DashboardType dashboardType,DashboardFilter dashboardFilter){
        Objects.requireNonNull(dashboardType,()->"invalid input");
        Objects.requireNonNull(dashboardFilter,()->"invalid input");
        return dashboardType.dashboardAlgo.apply(this,dashboardFilter);
    }
    public List<Person> getAdminDashboard(DashboardFilter dashboardFilter) {
        return people.stream()
                .filter(person -> person.getStatus() != null)
                .filter(person -> "ALL".equals(person.getStatus()))
                .collect(toList());
    }
    public List<Person> getUserDashboard(DashboardFilter dashboardFilter) {
        return people.stream()
                .filter(person -> person.getStatus() != null)
                .filter(person -> "APPLIED".equals(person.getStatus()))
                .collect(toList());
    }

    public List<Person> getSuperAdminDashboard(DashboardFilter dashboardFilter) {
        return people.stream()
                .filter(person -> person.getStatus() != null)
                .filter(person -> "NOT_APPLIED".equals(person.getStatus()))
                .collect(toList());
    }
}

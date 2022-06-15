package src.util;

import src.model.Person;
import src.service.PersonService;

import java.util.List;
import java.util.function.BiFunction;

public class EnumUtils {

    public enum DashboardType{
        ADMIN(PersonService::getAdminDashboard),
        USER(PersonService::getUserDashboard),
        SUPER_ADMIN(PersonService::getSuperAdminDashboard);
        public final BiFunction<PersonService,DashboardFilter, List<Person>> dashboardAlgo;

        DashboardType(BiFunction<PersonService, DashboardFilter, List<Person>> dashboardAlgo) {
            this.dashboardAlgo = dashboardAlgo;
        }
    }
}

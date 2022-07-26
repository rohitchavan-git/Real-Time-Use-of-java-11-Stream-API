package src.repo;

import src.model.Address;
import src.model.Person;

import java.util.List;

import static src.model.PersonBuilder.aPerson;

public class PersonRepo {
    public static List<Person> getPeoples() {
        return List.of(aPerson().fname("rohit").lanme("chavan").address(new Address("MH", "Osmanabad", 413501)).phoneNumber(List.of(80586, 805432)).age(25).build(),
                aPerson().fname("urmila").lanme("chavan").address(new Address("MH", "Pune", 411057)).phoneNumber(List.of(80585, 805431)).age(25).build(),
                aPerson().fname("Rahul").lanme("chavan").address(new Address("MH", "Osmanabad", 54666)).phoneNumber(List.of(80544, 845432)).age(22).build(),
                aPerson().fname("Amol").lanme("gade").address(new Address("MH", "shirdi", 76549)).phoneNumber(List.of(9875, 6744)).age(25).build(),
                aPerson().fname("ram").lanme("cena").address(new Address(null, "USA", 413501)).phoneNumber(List.of(80586, 805432)).age(43).build(),
                aPerson().fname("randy").lanme("ortan").address(null).phoneNumber(List.of(80586, 805432)).age(32).build(),
                aPerson().fname("rko").lanme("chavan").address(new Address("MH", null, 413501)).phoneNumber(null).age(31).build());
    }

}

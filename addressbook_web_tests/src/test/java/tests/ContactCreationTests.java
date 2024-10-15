package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("","First name test")) {
            for (var lastname : List.of("", "Last name test")) {
                    result.add(new ContactData().withFirstName(firstname).withLastName(lastname));
                }
            }
        for (int i = 0; i < 5; i++ ) {
            result.add(new ContactData().withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        //int contactCount = app.contacts().getCount();
       // app.contacts().createContact(contact);
       // int newContactCount = app.contacts().getCount();
       // Assertions.assertEquals(contactCount + 1, newContactCount);
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));};
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withFirstName("").withLastName(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "", "")));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CannotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}

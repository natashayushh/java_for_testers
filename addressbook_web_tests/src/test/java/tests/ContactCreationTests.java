package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.management.MXBean;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("","First name test")) {
            for (var lastname : List.of("", "Last name test")) {
                for (var mobile : List.of("", "89876543210")) {
                    result.add(new ContactData(firstname, lastname, mobile));
                }
            }
        }
        for (int i = 0; i < 5; i++ ) {
            result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("first name'", "", "")));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void CannotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
}

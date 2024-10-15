package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test
    public void CanDeleteContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First name", "Last name", "89990001122"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    void CanDeleteAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First name", "Last name", "89811112233"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}

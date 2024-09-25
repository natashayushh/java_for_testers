package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    public void CanDeleteContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("First name", "Last name", "88889990000"));
        }
        app.contacts().removeContact();
    }
}
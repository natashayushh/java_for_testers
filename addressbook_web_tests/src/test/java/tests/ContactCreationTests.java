package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void CanCreateContact() {
        app.contacts().createContact(new ContactData("First name test", "Last name test", "89876543210"));
    }
}

package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{
    @Test
    void TestPhones() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "", "Testfirstname", "Testlastname", "", "88003332211", "898219819811", "5554433", "lala@ya.ru", "", "kva", "lalalastreet"));;
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
        }

    @Test
    void TestAddress() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "", "Testfirstname", "Testlastname", "", "88003332211", "898219819811", "5554433", "lala@ya.ru", "", "kva", "lalalastreet"));;
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var address = app.contacts().getAddress();
        Assertions.assertEquals(expected, address);
    }

    @Test
    void TestEmails() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "", "Testfirstname", "Testlastname", "", "88003332211", "898219819811", "5554433", "lala@ya.ru", "", "kva", "lalalastreet"));;
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var emails = app.contacts().getEmails();
        Assertions.assertEquals(expected, emails);
    }

}

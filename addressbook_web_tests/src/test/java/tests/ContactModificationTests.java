package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First name", "Last name", "", "", "", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @Test
    public void CanAddContactToGroup() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10)));
        } if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData()
                    .withHeader(CommonFunctions.randomString(10))
                    .withFooter(CommonFunctions.randomString(10)));
        }
        var contact = app.hbm().getContactList().get(0);
        var group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactsInGroup(group).contains(contact)) {
            app.contacts().removeContactFromGroup(contact, group);
        }
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    public void CanRemoveContactFromGroup() {
        if (app.contacts().getCount() == 0) {
            var contact = (new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10)));
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData()
                        .withHeader(CommonFunctions.randomString(10))
                        .withFooter(CommonFunctions.randomString(10)));
                var group = app.hbm().getGroupList().get(0);
                app.contacts().createContactInGroup(contact, group);
            }
        }
            var group = app.hbm().getGroupList().get(0);
            var oldRelated = app.hbm().getContactsInGroup(group);
            var rnd = new Random();
            var index = rnd.nextInt(oldRelated.size());

            app.contacts().removeContactFromGroup(oldRelated.get(index), group);
            var newRelated = app.hbm().getContactsInGroup(group);
            var expectedList = new ArrayList<>(oldRelated);
            expectedList.remove(index);
            Assertions.assertEquals(newRelated, expectedList);
    }
}

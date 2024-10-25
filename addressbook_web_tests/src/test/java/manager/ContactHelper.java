package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void create(ContactData contact, GroupData group) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }
    private void selectGroupAtHomepage(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        returnToHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href=\"edit.php?id=%s\"]", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void removeContact(ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        //attach(By.name("photo"), contact.photo());
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        click(By.xpath("//input[@id='MassCB']"));
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectContact(contact);
        selectGroupAtHomepage(group);
        click(By.xpath("//input[@name='add']"));
        returnToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        returnToHomePage();
        selectGroupToRemoveContact(group);
        selectContact(contact);
        click(By.xpath("//input[@name='remove']"));
        returnToHomePage();
    }
    private void selectGroupToRemoveContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public List<ContactData> getList() {
        returnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var trline = manager.driver.findElements(By.name("entry"));
        for (var tr : trline) {
            var cell = tr.findElements(By.cssSelector("td"));
            var lastname = cell.get(1).getText();
            var firstname = cell.get(2).getText();
            var idreal = cell.get(0);
            var checkbox = idreal.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

}
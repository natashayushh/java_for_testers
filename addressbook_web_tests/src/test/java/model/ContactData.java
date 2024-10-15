package model;

import org.openqa.selenium.WebElement;

public record ContactData(String id, String firstname, String lastname, String mobile) {
    public ContactData() { this("", "", "", "");}

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.mobile);
    }
    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.mobile);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, mobile);
    }
}
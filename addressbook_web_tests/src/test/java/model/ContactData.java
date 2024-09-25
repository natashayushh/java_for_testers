package model;

public record ContactData(String firstname, String lastname, String mobile) {
    public ContactData() {
        this("", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.mobile);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, lastname, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.lastname, mobile);
    }
}
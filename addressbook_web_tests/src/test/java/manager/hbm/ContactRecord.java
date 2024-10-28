package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    public ContactRecord() {
    }

    @Id
    @Column(name = "id")
    public int id;
    public String firstname;
    public String lastname;
    public String home;
    public String mobile;
    public String work;
    public String email;
    public String email2;
    public String email3;
    public String address;

    public Date deprecated = new Date();


    public ContactRecord(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}

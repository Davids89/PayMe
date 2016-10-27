package luque.david.androidchat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Map;

/**
 * Created by david on 26/10/16.
 */
@JsonIgnoreProperties({"sentByMe"})
public class Deal {

    private String email_author;
    private Map<String, Boolean> contacts;
    private Float amount;
    private Date date_created;
    private String info;

    public Deal() {

    }

    public String getEmail_author() {
        return email_author;
    }

    public void setEmail_author(String email_author) {
        this.email_author = email_author;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

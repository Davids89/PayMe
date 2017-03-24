package luque.david.payme.entities;

import java.util.Map;

/**
 * Created by David on 19/9/16.
 */
public class User {
    private String email;
    private Map<String, Boolean> contacts;

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public final static boolean OFFLINE = false;
}

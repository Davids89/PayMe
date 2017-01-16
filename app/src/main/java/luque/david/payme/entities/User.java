package luque.david.payme.entities;

import java.util.Map;

/**
 * Created by David on 19/9/16.
 */
public class User {
    private String email;
    private boolean online;
    private Map<String, Boolean> contacts;

    public final static boolean ONLINE = true;

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public final static boolean OFFLINE = false;

    public User() {

    }
}

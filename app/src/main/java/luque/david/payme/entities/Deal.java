package luque.david.payme.entities;

import android.net.Uri;

import java.util.Date;
import java.util.Map;

/**
 * Created by david on 26/10/16.
 */
public class Deal {

    private String dealId;
    private String name;
    private Map<String, Boolean> contacts;
    private String amount;
    private String date_created;
    private String info;
    private Uri image;

    public Deal() {

    }

    @Override
    public boolean equals(Object o) {
        Boolean equal = false;

        if(o instanceof Deal){
            Deal deal = (Deal)o;
            equal = this.getDealId().equals(deal.getDealId());
        }

        return equal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}

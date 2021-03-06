package luque.david.payme.addDeal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import luque.david.payme.addDeal.events.AddDealEvents;
import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 26/10/16.
 */

public class AddDealRepositoryImpl implements AddDealRepository {

    private FirebaseHelper helper;
    private EventBus eventBus;

    public AddDealRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void createDeal(String name, String price, String info) {

        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        String date = dateFormat.format(new Date());

        Deal newDeal = new Deal();

        newDeal.setName(name);
        newDeal.setAmount(price);
        newDeal.setInfo(info);
        newDeal.setDate_created(date);

        Map<String, Boolean> contacts = new HashMap<String, Boolean>();
        contacts.put("bb@bb_com", false);
        newDeal.setContacts(contacts);

        DatabaseReference dealsReference = helper.getMyDealsReference();
        dealsReference.push().setValue(newDeal);

        dealsReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postError();
            }
        });

    }

    private void postSuccess(){
        post(false);
    }

    private void postError(){
        post(true);
    }

    private void post(boolean error){
        AddDealEvents event = new AddDealEvents();
        event.setError(error);
        eventBus.post(event);
    }
}

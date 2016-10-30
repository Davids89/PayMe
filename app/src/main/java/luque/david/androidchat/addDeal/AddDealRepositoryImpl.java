package luque.david.androidchat.addDeal;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import luque.david.androidchat.addDeal.events.AddDealEvents;
import luque.david.androidchat.domain.FirebaseHelper;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;

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
    public void createDeal(String name, String price) {

        final DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
        final Date date = new Date();
        final String dealId = helper.getAuthUserEmail().replace('.', '_');

        Deal newDeal = new Deal();

        newDeal.setDealId(dealId);
        newDeal.setName(name);
        newDeal.setAmount(price);

        Firebase dealsReference = helper.getDealsReference(dealId);
        dealsReference.push().setValue(newDeal);

        dealsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postSuccess();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
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

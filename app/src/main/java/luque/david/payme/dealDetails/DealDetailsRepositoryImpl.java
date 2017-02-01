package luque.david.payme.dealDetails;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import luque.david.payme.dealDetails.event.DealDetailsEvent;
import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 1/2/17.
 */

public class DealDetailsRepositoryImpl implements DealDetailsRepository {

    private EventBus eventBus;
    private DatabaseReference myDealReference;
    private FirebaseHelper helper;

    public DealDetailsRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();

    }

    @Override
    public void execute(String id) {
        this.myDealReference = helper.getMyDealReference(id);

        myDealReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Deal deal = dataSnapshot.getValue(Deal.class);
                postSuccess(DealDetailsEvent.onDealUpdated, deal);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postError(DealDetailsEvent.onDealDownloaded, databaseError.getMessage());
            }
        });
    }

    private void postSuccess(int eventType, Deal deal){
        post(eventType, deal, null);
    }

    private void postError(int eventType, String error){
        post(eventType, null, error);
    }

    private void post(int eventType, Deal deal, String error){

        DealDetailsEvent event = new DealDetailsEvent();

        if(error != null){
            event.setError(error);
        }else{
            event.setDeal(deal);
            event.setEventType(eventType);
        }

        eventBus.post(event);
    }
}

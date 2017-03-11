package luque.david.payme.listDeals;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import luque.david.payme.listDeals.event.DealsListEvent;
import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 30/9/16.
 */

public class ListDealsRepositoryImpl implements ListDealsRepository {

    private FirebaseHelper helper;
    private ChildEventListener dealsEventListener;
    private DatabaseReference myDealsReference;
    private EventBus eventBus;
    private StorageReference storageReference;

    public ListDealsRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.myDealsReference = helper.getMyDealsReference();

    }

    @Override
    public void signOff() {
        helper.SignOff();
    }

    @Override
    public void subscribeToDealsListEvents() {
        if(dealsEventListener == null){
            dealsEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleDealsEvent(dataSnapshot, DealsListEvent.onDealAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleDealsEvent(dataSnapshot, DealsListEvent.onDealUpdated);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleDealsEvent(dataSnapshot, DealsListEvent.onDealRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }

        myDealsReference.addChildEventListener(dealsEventListener);
    }

    private void handleDealsEvent(DataSnapshot dataSnapshot, final int eventType) {
        String id = dataSnapshot.getKey();
        String name = (String) dataSnapshot.child("name").getValue();
        String price = (String) dataSnapshot.child("amount").getValue();
        String info = (String) dataSnapshot.child("info").getValue();

        final Deal deal = new Deal();
        deal.setAmount(price);
        deal.setName(name);
        deal.setDealId(id);
        deal.setInfo(info);

        post(eventType, deal);
    }

    private void post(int eventType, Deal deal) {
        DealsListEvent event = new DealsListEvent();
        event.setEventType(eventType);
        event.setDeal(deal);
        eventBus.post(event);
    }

    @Override
    public void unsubscribeToDealsListEvents() {
        if(dealsEventListener != null){
            myDealsReference.removeEventListener(dealsEventListener);
        }
    }

    @Override
    public void destroyListener() {
        dealsEventListener = null;
    }

}

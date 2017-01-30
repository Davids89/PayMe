package luque.david.payme.listDeals;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

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
    private EventBus eventBus;

    public ListDealsRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
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
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }

        helper.getMyDealsReference().addChildEventListener(dealsEventListener);
    }

    private void handleDealsEvent(DataSnapshot dataSnapshot, int eventType) {
        String id = (String) dataSnapshot.getKey();
        String name = (String) dataSnapshot.child("name").getValue();
        String price = (String) dataSnapshot.child("amount").getValue();
        String info = (String) dataSnapshot.child("info").getValue();

        Deal deal = new Deal();
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
            helper.getMyDealsReference().removeEventListener(dealsEventListener);
        }
    }

    @Override
    public void destroyListener() {
        dealsEventListener = null;
    }

}

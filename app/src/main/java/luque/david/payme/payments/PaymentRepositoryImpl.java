package luque.david.payme.payments;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;
import luque.david.payme.payments.event.PaymentEvent;

/**
 * Created by david on 11/1/17.
 */

public class PaymentRepositoryImpl implements PaymentRepository {

    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener childEventListener;

    public PaymentRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void subscribeToDealsListEvents() {
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handlePayments(dataSnapshot, PaymentEvent.onPaymentAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handlePayments(dataSnapshot, PaymentEvent.onPaymentUpdated);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handlePayments(dataSnapshot, PaymentEvent.onPaymentRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };

            helper.getMyPaymentsReference().addChildEventListener(childEventListener);
        }
    }

    private void handlePayments(DataSnapshot dataSnapshot, int eventType) {

        String userEmail = helper.getAuthUserEmail();
        userEmail = userEmail.replace('.','_');

        for(DataSnapshot data : dataSnapshot.getChildren()){
            final Deal deal = data.getValue(Deal.class);
            deal.setDealId(data.getKey());
            if(deal.getContacts() != null){
                Boolean isMine = deal.getContacts().get(userEmail);
                if(isMine != null){
                    post(eventType, deal);
                }
            }
        }
    }

    private void post(int eventType, Deal deal){
        PaymentEvent event = new PaymentEvent();
        event.setEventType(eventType);
        event.setDeal(deal);
        eventBus.post(event);
    }

    @Override
    public void unsubscribeToDealsListEvents() {

    }

    @Override
    public void destroyListener() {

    }
}

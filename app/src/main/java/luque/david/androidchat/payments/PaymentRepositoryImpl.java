package luque.david.androidchat.payments;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Arrays;

import luque.david.androidchat.domain.FirebaseHelper;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;
import luque.david.androidchat.payments.event.PaymentEvent;

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

    private void handlePayments(DataSnapshot dataSnapshot, int onPaymentRemoved) {

        ArrayList<String> contacts = (ArrayList<String>) dataSnapshot.child("contacts").getValue();

        String userEmail = helper.getAuthUserEmail();

        if(contacts != null){
            if(Arrays.asList(contacts).contains(userEmail)){
                Log.wtf("PAYMENTREPOSITORY", "ESTA METIDO");
            }
        }

    }

    @Override
    public void unsubscribeToDealsListEvents() {

    }

    @Override
    public void destroyListener() {

    }
}

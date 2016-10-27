package luque.david.androidchat.addDeal;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import luque.david.androidchat.domain.FirebaseHelper;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.entities.User;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;

/**
 * Created by david on 26/10/16.
 */

public class AddDealRepositoryImpl implements AddDealRepository {

    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener dealsEventListener;

    public AddDealRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void createDeal() {

        String email = helper.getAuthUserEmail().replace('.', '_');
        Deal newDeal = new Deal();

        newDeal.setEmail_author(email);

        Firebase dealsReference = helper.getDealsReference(email);
        dealsReference.push().setValue(newDeal);

    }

    @Override
    public void subscribe() {
        if(dealsEventListener == null){
            dealsEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //TODO
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }

        helper.getDealsReference(helper.getAuthUserEmail()).addChildEventListener(dealsEventListener);
    }

    @Override
    public void unsubscribe() {
        if(dealsEventListener != null){
            helper.getDealsReference(helper.getAuthUserEmail()).removeEventListener(dealsEventListener);
        }
    }

    @Override
    public void destroyListener() {
        dealsEventListener = null;
    }
}

package luque.david.androidchat.domain;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.view.QuerySpec;

import java.util.HashMap;
import java.util.Map;

import luque.david.androidchat.entities.User;

/**
 * Created by David on 12/9/16.
 */
public class FirebaseHelper {
    private Firebase dataReference;
    private final static String FIREBASE_URL = "https://android-payme.firebaseio.com/";
    private final static String USERS_PATH = "users";
    private final static String DEALS_PATH = "deals";
    private final static String FRIENDS_PATH = "friends";

    private static class SingletoneHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletoneHolder.INSTANCE;
    }

    public FirebaseHelper(){
        this.dataReference = new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail(){
        AuthData authData = dataReference.getAuth();
        String email = null;

        if(authData != null){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }

        return email;
    }

    public Firebase getUserReference(String email){
        Firebase userReference = null;
        if(email != null){
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }

        return userReference;
    }

    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public Firebase getDealsReference(String email){

        String key = email.replace('.', '_');

        return dataReference.getRoot().child(DEALS_PATH).child(key);
    }

    public Firebase getMyDealsReference(){
        return getDealsReference(getAuthUserEmail());
    }

    public Firebase getMyPaymentsReference(){
       return getPaymentReference(getAuthUserEmail());
    }

    private Firebase getPaymentReference(String email) {
        return dataReference.getRoot().child(DEALS_PATH);
    }


    public void SignOff(){
        getMyDealsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataReference.unauth();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}

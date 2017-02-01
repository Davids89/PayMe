package luque.david.payme.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by David on 12/9/16.
 */
public class FirebaseHelper {
    private DatabaseReference dataReference;
    private final static String FIREBASE_URL = "https://payme-ff851.firebaseio.com/";
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
        this.dataReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(FIREBASE_URL);
    }

    public FirebaseAuth getAuthReference(){
        return FirebaseAuth.getInstance();
    }

    public DatabaseReference getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = null;

        if(user != null){
            email = user.getEmail();
        }

        return email;
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if(email != null){
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }

        return userReference;
    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getDealsReference(String email){

        String key = email.replace('.', '_');

        return dataReference.getRoot().child(DEALS_PATH).child(key);
    }

    public DatabaseReference getMyDealsReference(){
        return getDealsReference(getAuthUserEmail());
    }

    public DatabaseReference getMyPaymentsReference(){
       return getPaymentReference(getAuthUserEmail());
    }

    private DatabaseReference getPaymentReference(String email) {
        return dataReference.getRoot().child(DEALS_PATH);
    }


    public void SignOff(){
        FirebaseAuth.getInstance().signOut();
    }
}

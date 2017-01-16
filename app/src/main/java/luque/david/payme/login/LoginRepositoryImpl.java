package luque.david.payme.login;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.User;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;
import luque.david.payme.login.events.LoginEvent;

/**
 * Created by David on 14/9/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper firebaseHelper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.dataReference = firebaseHelper.getDataReference();
        this.myUserReference = firebaseHelper.getMyUserReference();
    }

    @Override
    public void singUp(final String email, final String password) {
        dataReference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>(){
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                singIn(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUnError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void singIn(String email, String password) {
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler(){

            @Override
            public void onAuthenticated(AuthData authData) {
                myUserReference = firebaseHelper.getMyUserReference();
                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser = dataSnapshot.getValue(User.class);

                        if(currentUser != null){
                            String email = firebaseHelper.getAuthUserEmail();
                            if(email != null){
                                currentUser = new User();
                                myUserReference.setValue(currentUser);
                            }
                        }
                        postEvent(LoginEvent.onSignInSuccess);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {

        AuthData data = dataReference.getAuth();

        if(dataReference.getAuth() != null && firebaseHelper.getAuthUserEmail() != null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailToRecoverSession);
        }
    }

    private void initSignIn(){
        myUserReference = firebaseHelper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}

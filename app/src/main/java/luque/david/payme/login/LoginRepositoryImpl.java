package luque.david.payme.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.User;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;
import luque.david.payme.login.events.LoginEvent;
import luque.david.payme.login.ui.LoginActivity;

/**
 * Created by David on 14/9/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper firebaseHelper;
    private DatabaseReference dataReference;
    private DatabaseReference myUserReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;

    public LoginRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.dataReference = firebaseHelper.getDataReference();
        this.myUserReference = firebaseHelper.getMyUserReference();
        this.mAuth = firebaseHelper.getAuthReference();
    }

    @Override
    public void singUp(final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            postEvent(LoginEvent.onSignUnError, task.getException().getLocalizedMessage());
                        }else{
                            postEvent(LoginEvent.onSignUpSuccess);
                        }
                    }
                });

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("LOGIN", "LOGI");
            }
        };

        mAuth.addAuthStateListener(listener);
    }

    @Override
    public void singIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            postEvent(LoginEvent.onSignInError, task.getException().getLocalizedMessage());
                        }else{
                            postEvent(LoginEvent.onSignInSuccess);
                        }

                    }
                });

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null && firebaseHelper.getUser() == null){
                    firebaseHelper.setUser(user);
                }
            }
        };

        mAuth.addAuthStateListener(listener);
    }

    @Override
    public void checkSession() {
        if(mAuth != null && mAuth.getCurrentUser() != null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailToRecoverSession);
        }
    }

    private void initSignIn(){
        firebaseHelper.setUser(mAuth.getCurrentUser());
        myUserReference = firebaseHelper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

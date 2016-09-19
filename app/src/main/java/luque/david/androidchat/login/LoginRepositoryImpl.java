package luque.david.androidchat.login;

import android.util.Log;

import luque.david.androidchat.domain.FirebaseHelper;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;
import luque.david.androidchat.login.events.LoginEvent;

/**
 * Created by David on 14/9/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper firebaseHelper;

    public LoginRepositoryImpl() {
        this.firebaseHelper = firebaseHelper.getInstance();
    }

    @Override
    public void singUp(String email, String password) {
        postEvent(LoginEvent.onSignUpSuccess);
    }

    @Override
    public void singIn(String email, String password) {
        postEvent(LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailToRecoverSession);
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

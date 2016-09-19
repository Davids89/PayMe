package luque.david.androidchat.login;

import luque.david.androidchat.login.events.LoginEvent;

/**
 * Created by David on 14/9/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}

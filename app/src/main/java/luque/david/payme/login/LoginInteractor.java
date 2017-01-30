package luque.david.payme.login;

/**
 * Created by David on 14/9/16.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}

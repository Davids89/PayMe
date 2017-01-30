package luque.david.payme.login;

/**
 * Created by David on 14/9/16.
 */
public interface LoginRepository {
    void singUp(String email, String password);
    void singIn(String email, String password);
    void checkSession();
}

package luque.david.androidchat.login;

import android.util.Log;

import luque.david.androidchat.domain.FirebaseHelper;

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
        Log.d("LoginRepositoryImpl", "Sign up");
    }

    @Override
    public void singIn(String email, String password) {
        Log.d("LoginRepositoryImpl", "Sign in");
    }

    @Override
    public void checkSession() {
        Log.d("LoginRepositoryImpl", "Check session");
    }
}

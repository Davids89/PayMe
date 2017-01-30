package luque.david.payme.login;

/**
 * Created by David on 14/9/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.singUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.singIn(email, password);
    }
}

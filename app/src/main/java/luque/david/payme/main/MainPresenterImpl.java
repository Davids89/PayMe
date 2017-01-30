package luque.david.payme.main;

/**
 * Created by david on 10/1/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainInteractor interactor;

    public MainPresenterImpl() {
        interactor = new MainInteractorImpl();
    }

    @Override
    public void singOff() {
        interactor.singOff();
    }
}

package luque.david.androidchat.deals;

/**
 * Created by david on 30/9/16.
 */

public class DealsPresenterImpl implements DealsPresenter {

    private DealsInteractor interactor;

    public DealsPresenterImpl() {
        this.interactor = new DealsInteractorImpl();
    }

    @Override
    public void signOff() {
        interactor.signOff();
    }

    @Override
    public void addDeal() {

    }

    @Override
    public void selectDeal() {

    }
}

package luque.david.androidchat.deals;

/**
 * Created by david on 30/9/16.
 */

public class DealsInteractorImpl implements DealsInteractor {

    private DealsRepository repository;

    public DealsInteractorImpl() {
        this.repository = new DealsRepositoryImpl();
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public void subscribe() {
        repository.subscribeToDealsListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToDealsListEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}

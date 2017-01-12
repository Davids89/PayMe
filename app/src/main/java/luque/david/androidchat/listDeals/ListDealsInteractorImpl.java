package luque.david.androidchat.listDeals;

/**
 * Created by david on 30/9/16.
 */

public class ListDealsInteractorImpl implements ListDealsInteractor {

    private ListDealsRepository repository;

    public ListDealsInteractorImpl() {
        this.repository = new ListDealsRepositoryImpl();
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

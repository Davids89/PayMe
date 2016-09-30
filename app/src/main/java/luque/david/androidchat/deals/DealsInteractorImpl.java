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
}

package luque.david.androidchat.addDeal;

/**
 * Created by david on 25/10/16.
 */

public class AddDealInteractorImpl implements AddDealInteractor{

    AddDealRepository repository;

    public AddDealInteractorImpl() {
        this.repository = new AddDealRepositoryImpl();
    }

    @Override
    public void createDeal() {
        repository.createDeal();
    }
}

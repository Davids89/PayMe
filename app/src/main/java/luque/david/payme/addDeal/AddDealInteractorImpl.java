package luque.david.payme.addDeal;

/**
 * Created by david on 25/10/16.
 */

public class AddDealInteractorImpl implements AddDealInteractor{

    AddDealRepository repository;

    public AddDealInteractorImpl() {
        this.repository = new AddDealRepositoryImpl();
    }

    @Override
    public void createDeal(String name, String price, String info) {
        repository.createDeal(name, price, info);
    }
}

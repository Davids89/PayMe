package luque.david.payme.dealDetails;

/**
 * Created by david on 1/2/17.
 */

public class DealDetailsInteractorImpl implements DealDetailsInteractor {

    private DealDetailsRepository repository;

    public DealDetailsInteractorImpl() {
        this.repository = new DealDetailsRepositoryImpl();
    }

    @Override
    public void execute(String id) {
        repository.execute(id);
    }
}

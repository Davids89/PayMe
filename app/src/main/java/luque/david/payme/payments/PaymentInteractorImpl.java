package luque.david.payme.payments;

/**
 * Created by david on 11/1/17.
 */

public class PaymentInteractorImpl implements PaymentInteractor {

    private PaymentRepository repository;

    public PaymentInteractorImpl() {
        this.repository = new PaymentRepositoryImpl();
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

package luque.david.payme.payments;

/**
 * Created by david on 11/1/17.
 */

public interface PaymentInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
}

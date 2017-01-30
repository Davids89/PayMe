package luque.david.payme.payments;

/**
 * Created by david on 11/1/17.
 */

public interface PaymentRepository {
    void subscribeToDealsListEvents();
    void unsubscribeToDealsListEvents();
    void destroyListener();
}

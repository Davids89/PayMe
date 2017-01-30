package luque.david.payme.payments.ui;

import luque.david.payme.entities.Deal;

/**
 * Created by david on 11/1/17.
 */

public interface PaymentView {
    void onPaymentAdded(Deal deal);
    void onPaymentUpdated(Deal deal);
    void onPaymentRemoved(Deal deal);
}

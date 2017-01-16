package luque.david.payme.payments;

import luque.david.payme.payments.event.PaymentEvent;

/**
 * Created by david on 11/1/17.
 */

public interface PaymentPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void onMainThread(PaymentEvent event);
}

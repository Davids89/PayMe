package luque.david.androidchat.payments;

import org.greenrobot.eventbus.Subscribe;

import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;
import luque.david.androidchat.payments.event.PaymentEvent;
import luque.david.androidchat.payments.ui.PaymentView;

/**
 * Created by david on 11/1/17.
 */

public class PaymentPresenterImpl implements PaymentPresenter {

    private PaymentInteractor interactor;
    private EventBus eventBus;
    private PaymentView view;

    public PaymentPresenterImpl(PaymentView view) {
        this.interactor = new PaymentInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
    }

    @Override
    public void onPause() {
        this.interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        this.interactor.subscribe();
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Subscribe
    @Override
    public void onMainThread(PaymentEvent event) {

        Deal deal = event.getDeal();

        switch (event.getEventType()){
            case PaymentEvent.onPaymentAdded:
                view.onPaymentAdded(deal);
                break;
            case PaymentEvent.onPaymentUpdated:
                view.onPaymentUpdated(deal);
                break;
            case PaymentEvent.onPaymentRemoved:
                view.onPaymentRemoved(deal);
                break;
        }
    }
}

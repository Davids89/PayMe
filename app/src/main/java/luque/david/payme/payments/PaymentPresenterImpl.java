package luque.david.payme.payments;

import org.greenrobot.eventbus.Subscribe;

import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;
import luque.david.payme.payments.event.PaymentEvent;
import luque.david.payme.payments.ui.PaymentView;

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

    @Override
    @Subscribe
    public void onMainThread(PaymentEvent event) {

        if(view != null){
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
}

package luque.david.androidchat.deals;

import org.greenrobot.eventbus.Subscribe;

import luque.david.androidchat.deals.event.DealsListEvent;
import luque.david.androidchat.deals.ui.DealsView;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.lib.EventBus;
import luque.david.androidchat.lib.GreenRobotEventBus;

/**
 * Created by david on 30/9/16.
 */

public class DealsPresenterImpl implements DealsPresenter {

    private DealsInteractor interactor;
    private EventBus eventBus;
    private DealsView dealsView;

    public DealsPresenterImpl(DealsView view) {
        this.interactor = new DealsInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.dealsView = view;
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        interactor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        dealsView = null;
    }

    @Override
    public void signOff() {
        interactor.signOff();
    }

    @Override
    public void addDeal() {

    }

    @Override
    public void selectDeal() {

    }

    @Override
    @Subscribe
    public void onEventMainThred(DealsListEvent event) {
        Deal deal = event.getDeal();
        switch (event.getEventType()){
            case DealsListEvent.onDealAdded:
                dealsView.onDealAdded(deal);
        }
    }
}

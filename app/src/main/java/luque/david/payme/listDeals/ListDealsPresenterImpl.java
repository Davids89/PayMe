package luque.david.payme.listDeals;

import org.greenrobot.eventbus.Subscribe;

import luque.david.payme.listDeals.event.DealsListEvent;
import luque.david.payme.listDeals.ui.ListDealsView;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 30/9/16.
 */

public class ListDealsPresenterImpl implements ListDealsPresenter {

    private ListDealsInteractor interactor;
    private EventBus eventBus;
    private ListDealsView dealsView;

    public ListDealsPresenterImpl(ListDealsView view) {
        this.interactor = new ListDealsInteractorImpl();
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
                break;
            case DealsListEvent.onDealUpdated:
                dealsView.onDealChanged(deal);
                break;
            case DealsListEvent.onDealRemoved:
                dealsView.onDealRemoved(deal);
                break;
        }
    }
}

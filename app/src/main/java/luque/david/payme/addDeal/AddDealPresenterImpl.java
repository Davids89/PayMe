package luque.david.payme.addDeal;

import org.greenrobot.eventbus.Subscribe;

import luque.david.payme.addDeal.events.AddDealEvents;
import luque.david.payme.addDeal.ui.AddDealView;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 25/10/16.
 */

public class AddDealPresenterImpl implements AddDealPresenter {

    private EventBus eventBus;
    private AddDealView view;
    private AddDealInteractor interactor;

    public AddDealPresenterImpl(AddDealView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddDealInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void addDeal(String name, String price, String info) {
        if(view != null){
            view.showProgress();
            view.hideInput();
        }
        interactor.createDeal(name, price, info);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddDealEvents event) {
        if(view != null){
            view.hideProgress();
            view.showInput();

            if(event.isError()){
                view.dealNotAdded();
            }else{
                view.dealAdded();
            }
        }
    }
}

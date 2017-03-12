package luque.david.payme.dealDetails;

import android.graphics.Bitmap;

import org.greenrobot.eventbus.Subscribe;

import luque.david.payme.dealDetails.event.DealDetailsEvent;
import luque.david.payme.dealDetails.ui.DealsDetailsView;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 1/2/17.
 */

public class DealDetailsPresenterImpl implements DealDetailsPresenter {

    private DealDetailsInteractor interactor;
    private DealsDetailsView view;
    private EventBus eventBus;

    public DealDetailsPresenterImpl(DealsDetailsView view) {
        this.interactor = new DealDetailsInteractorImpl();
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate(String id) {
        eventBus.register(this);
        if(view != null){
            interactor.execute(id);
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(DealDetailsEvent event) {
        switch (event.getEventType()) {
            case DealDetailsEvent.onDealUpdated:
                Deal deal = event.getDeal();
                view.setToolbar(deal.getName());
                view.setDealDetails(event.getDeal());
                break;
            case DealDetailsEvent.onImageAdded:
                view.onImageAdded();
                break;
        }
    }

    @Override
    public void takePicture(Bitmap photo, String dealId) {
        if(view != null){
            interactor.takePicture(photo, dealId);
        }
    }
}

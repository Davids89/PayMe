package luque.david.payme.addDeal;

import luque.david.payme.addDeal.events.AddDealEvents;

/**
 * Created by david on 25/10/16.
 */

public interface AddDealPresenter {
    void onCreate();
    void onDestroy();

    void addDeal(String name, String price, String info);
    void onEventMainThread(AddDealEvents event);
}

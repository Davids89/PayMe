package luque.david.androidchat.addDeal;

import luque.david.androidchat.addDeal.events.AddDealEvents;

/**
 * Created by david on 25/10/16.
 */

public interface AddDealPresenter {
    void onShow();
    void onDestroy();

    void addDeal();
    void onEventMainThread(AddDealEvents event);
}

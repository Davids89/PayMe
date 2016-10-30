package luque.david.androidchat.deals;

import luque.david.androidchat.deals.event.DealsListEvent;

/**
 * Created by david on 30/9/16.
 */

public interface DealsPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    void addDeal();
    void selectDeal();
    void onEventMainThred(DealsListEvent event);
}

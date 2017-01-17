package luque.david.payme.listDeals;

import luque.david.payme.listDeals.event.DealsListEvent;

/**
 * Created by david on 30/9/16.
 */

public interface ListDealsPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    void addDeal();
    void selectDeal();
    void onEventMainThred(DealsListEvent event);
}

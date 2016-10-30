package luque.david.androidchat.deals.ui;

import luque.david.androidchat.entities.Deal;

/**
 * Created by david on 30/9/16.
 */

public interface DealsView {
    void selectDeal();
    void onDealAdded(Deal deal);
    void onDealChanged(Deal deal);
    void onDealRemoved(Deal deal);
}

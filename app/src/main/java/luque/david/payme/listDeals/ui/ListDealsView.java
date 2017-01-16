package luque.david.payme.listDeals.ui;

import luque.david.payme.entities.Deal;

/**
 * Created by david on 30/9/16.
 */

public interface ListDealsView {
    void selectDeal();
    void onDealAdded(Deal deal);
    void onDealChanged(Deal deal);
    void onDealRemoved(Deal deal);
}

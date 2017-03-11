package luque.david.payme.dealDetails.ui;

import luque.david.payme.entities.Deal;

/**
 * Created by david on 1/2/17.
 */
public interface DealsDetailsView {
    void setToolbar(String name);
    void onImageAdded();
    void setDealDetails(Deal deal);
}

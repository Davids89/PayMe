package luque.david.payme.listDeals.adapter;

import luque.david.payme.entities.Deal;

/**
 * Created by david on 28/10/16.
 */

public interface OnItemClickListener {
    void onItemClick(Deal deal);
    void onItemLongClick(Deal deal);
}

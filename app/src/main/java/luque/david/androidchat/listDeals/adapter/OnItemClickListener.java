package luque.david.androidchat.listDeals.adapter;

import luque.david.androidchat.entities.Deal;

/**
 * Created by david on 28/10/16.
 */

public interface OnItemClickListener {
    void onItemClick(Deal deal);
    void onItemLongClick(Deal deal);
}

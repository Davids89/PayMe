package luque.david.androidchat.listDeals.event;

import luque.david.androidchat.entities.Deal;

/**
 * Created by david on 29/10/16.
 */

public class DealsListEvent {
    public final static int onDealAdded = 0;
    public final static int onDealUpdated = 1;
    public final static int onDealRemoved = 2;

    private Deal deal;
    private int eventType;

    public static int getOnDealAdded() {
        return onDealAdded;
    }

    public static int getOnDealUpdated() {
        return onDealUpdated;
    }

    public static int getOnDealRemoved() {
        return onDealRemoved;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}

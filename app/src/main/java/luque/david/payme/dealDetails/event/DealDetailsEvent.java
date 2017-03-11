package luque.david.payme.dealDetails.event;

import luque.david.payme.entities.Deal;

/**
 * Created by david on 1/2/17.
 */
public class DealDetailsEvent {
    public static final int onDealDownloaded = 0;
    public static final int onDealUpdated = 1;
    public static final int onImageAdded = 2;

    private Deal deal;
    private String error;
    private int eventType;

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}

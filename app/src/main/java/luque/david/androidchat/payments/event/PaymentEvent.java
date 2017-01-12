package luque.david.androidchat.payments.event;

import luque.david.androidchat.entities.Deal;

/**
 * Created by david on 11/1/17.
 */
public class PaymentEvent {
    public final static int onPaymentAdded = 0;
    public final static int onPaymentUpdated = 1;
    public final static int onPaymentRemoved = 2;

    private Deal deal;
    private int eventType;

    public static int getOnPaymentAdded() {
        return onPaymentAdded;
    }

    public static int getOnPaymentUpdated() {
        return onPaymentUpdated;
    }

    public static int getOnPaymentRemoved() {
        return onPaymentRemoved;
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

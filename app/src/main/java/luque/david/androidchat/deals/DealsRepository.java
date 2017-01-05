package luque.david.androidchat.deals;

/**
 * Created by david on 30/9/16.
 */

public interface DealsRepository {

    void signOff();
    void subscribeToDealsListEvents();
    void unsubscribeToDealsListEvents();
    void destroyListener();
}

package luque.david.androidchat.listDeals;

/**
 * Created by david on 30/9/16.
 */

public interface ListDealsRepository {

    void signOff();
    void subscribeToDealsListEvents();
    void unsubscribeToDealsListEvents();
    void destroyListener();
}

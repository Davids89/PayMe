package luque.david.androidchat.addDeal;

/**
 * Created by david on 26/10/16.
 */

public interface AddDealRepository {
    void createDeal();
    void subscribe();
    void unsubscribe();

    void destroyListener();
}

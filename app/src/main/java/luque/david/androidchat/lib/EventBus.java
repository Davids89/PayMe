package luque.david.androidchat.lib;

/**
 * Created by David on 16/9/16.
 */
public interface EventBus {
    void register(Object suscriber);
    void unregister(Object suscriber);
    void post(Object event);
}

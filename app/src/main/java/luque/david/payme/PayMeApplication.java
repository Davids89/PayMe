package luque.david.payme;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by David on 12/9/16.
 */
public class PayMeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}

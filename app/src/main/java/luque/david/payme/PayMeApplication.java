package luque.david.payme;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

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
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

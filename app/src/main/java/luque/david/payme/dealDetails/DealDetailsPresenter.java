package luque.david.payme.dealDetails;

import android.graphics.Bitmap;

import luque.david.payme.dealDetails.event.DealDetailsEvent;

/**
 * Created by david on 1/2/17.
 */

public interface DealDetailsPresenter {
    void onCreate(String id);
    void onDestroy();

    void onEventMainThread(DealDetailsEvent event);
    void takePicture(Bitmap photo, String dealId);
}

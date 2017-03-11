package luque.david.payme.dealDetails;

import android.graphics.Bitmap;

/**
 * Created by david on 1/2/17.
 */

public interface DealDetailsInteractor {
    void execute(String id);
    void takePicture(Bitmap photo, String dealId);
}

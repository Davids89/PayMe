package luque.david.payme.dealDetails;

import android.graphics.Bitmap;

/**
 * Created by david on 1/2/17.
 */

public interface DealDetailsRepository {
    void execute(String id);
    void uploadPhotoToDeal(Bitmap photo, String dealId);
}

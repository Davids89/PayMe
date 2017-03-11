package luque.david.payme.lib.base;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by david on 22/9/16.
 */
public interface ImageLoader {
    void load(ImageView imgAvatar, String url);
    void loadDealImage(ImageView imageView, Uri uri);
}

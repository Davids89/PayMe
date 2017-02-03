package luque.david.payme.lib;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import luque.david.payme.lib.base.ImageLoader;

/**
 * Created by david on 9/12/16.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
        glideRequestManager
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(600, 400)
                .into(imgAvatar);
    }

    @Override
    public void loadDealImage(ImageView imageView, Uri uri) {
        glideRequestManager
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }
}

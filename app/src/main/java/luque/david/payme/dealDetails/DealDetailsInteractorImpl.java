package luque.david.payme.dealDetails;

import android.graphics.Bitmap;

/**
 * Created by david on 1/2/17.
 */

public class DealDetailsInteractorImpl implements DealDetailsInteractor {

    private DealDetailsRepository repository;

    public DealDetailsInteractorImpl() {
        this.repository = new DealDetailsRepositoryImpl();
    }

    @Override
    public void execute(String id) {
        repository.execute(id);
    }

    @Override
    public void takePicture(Bitmap photo, String dealId) {
        repository.uploadPhotoToDeal(photo, dealId);
    }
}

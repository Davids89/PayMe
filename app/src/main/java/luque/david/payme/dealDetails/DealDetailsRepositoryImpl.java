package luque.david.payme.dealDetails;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import luque.david.payme.dealDetails.event.DealDetailsEvent;
import luque.david.payme.domain.FirebaseHelper;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.EventBus;
import luque.david.payme.lib.GreenRobotEventBus;

/**
 * Created by david on 1/2/17.
 */

public class DealDetailsRepositoryImpl implements DealDetailsRepository {

    private EventBus eventBus;
    private DatabaseReference myDealReference;
    private FirebaseHelper helper;
    private StorageReference storageReference;

    public DealDetailsRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void execute(String id) {
        this.myDealReference = helper.getMyDealReference(id);
        storageReference = FirebaseHelper.getStorageReference().child("image/" + id + ".jpg");

        myDealReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Deal deal = dataSnapshot.getValue(Deal.class);

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        deal.setImage(uri);
                        postSuccess(DealDetailsEvent.onDealUpdated, deal);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postError(DealDetailsEvent.onDealDownloaded, databaseError.getMessage());
            }
        });
    }

    @Override
    public void uploadPhotoToDeal(Bitmap photo, String dealId) {

        final String idImage = dealId.concat(".jpg");

        storageReference = FirebaseHelper.getStorageReference().child("image/" + idImage);

        final byte[] data = getImageCompressed(photo);

        UploadTask uploadTask = storageReference.putBytes(data);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                HashMap<String, Object> uriChildren = new HashMap<String, Object>();
                uriChildren.put("photo", idImage);

                myDealReference.updateChildren(uriChildren);

                post(DealDetailsEvent.onImageAdded, null, null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private byte[] getImageCompressed(Bitmap photo) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    private void postSuccess(int eventType, Deal deal){
        post(eventType, deal, null);
    }

    private void postError(int eventType, String error){
        post(eventType, null, error);
    }

    private void post(int eventType, Deal deal, String error){

        DealDetailsEvent event = new DealDetailsEvent();

        if(error != null){
            event.setError(error);
        }else{
            if(deal != null){
                event.setDeal(deal);
            }
            event.setEventType(eventType);
        }

        eventBus.post(event);
    }
}

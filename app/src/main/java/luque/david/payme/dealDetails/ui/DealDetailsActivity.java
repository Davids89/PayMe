package luque.david.payme.dealDetails.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luque.david.payme.R;
import luque.david.payme.dealDetails.DealDetailsPresenter;
import luque.david.payme.dealDetails.DealDetailsPresenterImpl;
import luque.david.payme.entities.Deal;
import luque.david.payme.lib.GlideImageLoader;

public class DealDetailsActivity extends AppCompatActivity implements DealsDetailsView{

    @Bind(R.id.deal_details_toolbar)
    Toolbar dealDetailsToolbar;
    @Bind(R.id.activity_deal_details)
    CoordinatorLayout activityDealDetails;
    @Bind(R.id.viewA)
    ImageView dealImage;

    private final static int DEAL_PICTURE = 1;
    private DealDetailsPresenter presenter;
    private String dealId;
    private GlideImageLoader loader;

    public DealDetailsActivity() {
        presenter = new DealDetailsPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);
        ButterKnife.bind(this);

        getDealId();
        presenter.onCreate(dealId);
        setGlide();
    }

    private void setGlide() {
        RequestManager requestManager = Glide.with(this);
        loader = new GlideImageLoader(requestManager);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void getDealId() {
        dealId = getIntent().getExtras().getString("ID");
    }

    @OnClick(R.id.viewA)
    public void onClickOnImage(){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, DEAL_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DEAL_PICTURE && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            dealImage.setImageBitmap(photo);
            presenter.takePicture(photo, dealId);
        }
    }

    @Override
    public void setToolbar(String name) {
        setSupportActionBar(dealDetailsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void onImageAdded() {
        Snackbar.make(activityDealDetails, R.string.dealdetails_image_added, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setDealDetails(Deal deal) {
        loader.loadDealImage(dealImage, deal.getImage());
    }
}

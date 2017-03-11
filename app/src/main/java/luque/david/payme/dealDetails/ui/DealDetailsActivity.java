package luque.david.payme.dealDetails.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luque.david.payme.R;
import luque.david.payme.dealDetails.DealDetailsPresenter;
import luque.david.payme.dealDetails.DealDetailsPresenterImpl;
import luque.david.payme.dealDetails.FriendAdapter;
import luque.david.payme.entities.Deal;
import luque.david.payme.entities.Friend;
import luque.david.payme.lib.GlideImageLoader;

public class DealDetailsActivity extends AppCompatActivity implements DealsDetailsView {

    @Bind(R.id.deal_details_toolbar)
    Toolbar dealDetailsToolbar;
    @Bind(R.id.activity_deal_details)
    CoordinatorLayout activityDealDetails;
    @Bind(R.id.viewA)
    ImageView dealImage;
    @Bind(R.id.dealTitle)
    TextView dealTitle;
    @Bind(R.id.dealDescription)
    TextView dealDescription;
    @Bind(R.id.friendsRecyclerView)
    RecyclerView friendsRecyclerView;

    private final static int DEAL_PICTURE = 1;
    private DealDetailsPresenter presenter;
    private String dealId;
    private GlideImageLoader loader;
    private FriendAdapter friendAdapter;

    public DealDetailsActivity() {
        presenter = new DealDetailsPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);
        ButterKnife.bind(this);

        getDealId();
        setupFriendAdapter();
        setupFriendRecyclerView();
        presenter.onCreate(dealId);
        setGlide();
    }

    private void setupFriendAdapter() {
        List<Friend> friends = new ArrayList<>();

        friendAdapter = new FriendAdapter(friends);
    }

    private void setupFriendRecyclerView() {
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsRecyclerView.setAdapter(friendAdapter);
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
    public void onClickOnImage() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, DEAL_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DEAL_PICTURE && resultCode == Activity.RESULT_OK) {
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

        List<Friend> friendsList = new ArrayList<>();

        loader.loadDealImage(dealImage, deal.getImage());

        dealTitle.setText(deal.getName());
        dealDescription.setText(deal.getInfo());

        Map<String, Boolean> friends = deal.getContacts();

        for(Map.Entry<String, Boolean> entry: friends.entrySet()){
            Friend friend = new Friend();
            friend.setEmail(entry.getKey());
            friendsList.add(friend);
        }

        friendAdapter.setupFriendList(friendsList);
    }
}

package luque.david.androidchat.deals.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luque.david.androidchat.R;
import luque.david.androidchat.addDeal.ui.AddDealFragment;
import luque.david.androidchat.deals.DealsPresenter;
import luque.david.androidchat.deals.DealsPresenterImpl;
import luque.david.androidchat.deals.adapter.DealsListAdapter;
import luque.david.androidchat.deals.adapter.OnItemClickListener;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.login.ui.LoginActivity;

public class DealsActivity extends AppCompatActivity implements DealsView, OnItemClickListener {

    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private DealsPresenter presenter;
    private DealsListAdapter adapter;

    public DealsActivity() {
        this.presenter = new DealsPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter = new DealsPresenterImpl(this);
        presenter.onCreate();
    }

    private void setupAdapter() {

        ArrayList<Deal> deals = new ArrayList<Deal>();

        adapter = new DealsListAdapter(deals, this);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deals, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void selectDeal() {

    }

    @Override
    public void onDealAdded(Deal deal) {
        adapter.add(deal);
    }

    @Override
    public void onDealChanged(Deal deal) {
        adapter.update(deal);
    }

    @Override
    public void onDealRemoved(Deal deal) {
        adapter.delete(deal);
    }

    @OnClick(R.id.fab)
    void addDeal(){
        new AddDealFragment().show(getSupportFragmentManager(), getString(R.string.adddeal_message_title));
    }

    @Override
    public void onItemClick(Deal deal) {

    }

    @Override
    public void onItemLongClick(Deal deal) {

    }
}

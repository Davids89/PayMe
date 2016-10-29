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
        this.presenter = new DealsPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
    }

    private void setupAdapter() {

        ArrayList<Deal> deals = new ArrayList<Deal>();
        Deal deal = new Deal();
        Deal deal2 = new Deal();
        deal.setName("Prueba");
        deal2.setName("Prueba 2");
        deal.setAmount("11");
        deal2.setAmount("22");
        deals.add(deal);
        deals.add(deal2);

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
    public void selectDeal() {

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

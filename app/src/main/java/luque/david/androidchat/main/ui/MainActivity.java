package luque.david.androidchat.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luque.david.androidchat.R;
import luque.david.androidchat.addDeal.ui.AddDealActivity;
import luque.david.androidchat.listDeals.ui.ListDealsFragment;
import luque.david.androidchat.login.ui.LoginActivity;
import luque.david.androidchat.main.MainPresenter;
import luque.david.androidchat.main.MainPresenterImpl;
import luque.david.androidchat.main.ui.adapter.MainSectionsPagerAdapter;
import luque.david.androidchat.payments.ui.PaymentFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupAdapter();
        getAdviceFromDealActivity();

        presenter = new MainPresenterImpl();
    }

    private void getAdviceFromDealActivity() {
        Intent intent = getIntent();

        if(intent.getExtras() != null){
            Boolean advice = intent.getExtras().getBoolean("ADVICE");

            if(advice)
                Snackbar.make(mainContent, R.string.adddeal_message_added, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{new ListDealsFragment(), new PaymentFragment()};
        String[] titles = new String[]{getString(R.string.main_header_mypays), getString(R.string.main_header_mydebts)};

        MainSectionsPagerAdapter adapter =
                new MainSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deals, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            presenter.singOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void addDeal() {
        Intent intent = new Intent(this, AddDealActivity.class);
        startActivity(intent);
        //new AddDealFragment().show(getSupportFragmentManager(), getString(R.string.adddeal_message_title));
    }


}

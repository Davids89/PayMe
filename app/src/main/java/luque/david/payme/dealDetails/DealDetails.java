package luque.david.payme.dealDetails;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.payme.R;

public class DealDetails extends AppCompatActivity {

    @Bind(R.id.deal_details_toolbar)
    Toolbar dealDetailsToolbar;
    @Bind(R.id.activity_deal_details)
    CoordinatorLayout activityDealDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);
        ButterKnife.bind(this);

        setToolbar();
    }

    private void setToolbar() {

        String name = getIntent().getExtras().getString("NAME");

        setSupportActionBar(dealDetailsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);
    }
}

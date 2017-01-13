package luque.david.androidchat.addDeal.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.androidchat.R;

public class AddDealActivity extends AppCompatActivity {

    @Bind(R.id.add_deal_toolbar)
    Toolbar addDealToolbar;
    @Bind(R.id.editTxtName)
    EditText editTxtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);
        ButterKnife.bind(this);

        setSupportActionBar(addDealToolbar);
    }
}

package luque.david.androidchat.addDeal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luque.david.androidchat.R;
import luque.david.androidchat.addDeal.AddDealPresenter;
import luque.david.androidchat.addDeal.AddDealPresenterImpl;
import luque.david.androidchat.main.ui.MainActivity;

public class AddDealActivity extends AppCompatActivity implements AddDealView {

    @Bind(R.id.add_deal_toolbar)
    Toolbar addDealToolbar;
    @Bind(R.id.editTxtName)
    EditText editTxtName;
    @Bind(R.id.editTxtPrice)
    EditText editTxtPrice;
    @Bind(R.id.editTxtInfo)
    EditText editTxtInfo;
    @Bind(R.id.add_deal_content)
    LinearLayout addDealContent;

    private AddDealPresenter presenter;
    final static Boolean SEND_ADVICE = true;
    final static Boolean NOT_SEND_ADVICE = false;

    public AddDealActivity() {
        presenter = new AddDealPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deal);
        ButterKnife.bind(this);

        setupToolbar();

        presenter.onCreate();
    }

    private void setupToolbar() {
        setSupportActionBar(addDealToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.add_deal_cancel)
    public void onCancelButton() {
        changeToMainScreen(NOT_SEND_ADVICE);
    }

    @OnClick(R.id.add_deal_accept)
    public void onAcceptButton() {
        String name = editTxtName.getText().toString();
        String price = editTxtPrice.getText().toString();
        String info = editTxtInfo.getText().toString();

        presenter.addDeal(name, price, info);
    }

    private void changeToMainScreen(Boolean advice){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if(advice){
            intent.putExtra("ADVICE", advice);
        }
        startActivity(intent);
    }

    @Override
    public void showInput() {
        editTxtInfo.setEnabled(true);
        editTxtName.setEnabled(true);
        editTxtPrice.setEnabled(true);
    }

    @Override
    public void hideInput() {
        editTxtInfo.setEnabled(false);
        editTxtName.setEnabled(false);
        editTxtPrice.setEnabled(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void dealAdded() {
        changeToMainScreen(SEND_ADVICE);
    }

    @Override
    public void dealNotAdded() {

    }
}

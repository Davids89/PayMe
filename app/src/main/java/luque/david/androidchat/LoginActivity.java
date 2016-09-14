package luque.david.androidchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;

    @Bind(R.id.editTxtPassword)
    EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSinging)
    public void handleSinging(){
        Log.e("AndroidChat", inputEmail.getText().toString());
    }

    @OnClick(R.id.btnSingup)
    public void handleSingup(){

    }
}

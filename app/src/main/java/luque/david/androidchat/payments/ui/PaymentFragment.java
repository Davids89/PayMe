package luque.david.androidchat.payments.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import luque.david.androidchat.R;
import luque.david.androidchat.payments.PaymentPresenter;
import luque.david.androidchat.payments.PaymentPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements PaymentView {

    private PaymentPresenter presenter;


    public PaymentFragment() {
        // Required empty public constructor
        presenter = new PaymentPresenterImpl(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        presenter.onCreate();
        return textView;
    }

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}

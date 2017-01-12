package luque.david.androidchat.payments.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.androidchat.R;
import luque.david.androidchat.entities.Deal;
import luque.david.androidchat.payments.PaymentPresenter;
import luque.david.androidchat.payments.PaymentPresenterImpl;
import luque.david.androidchat.payments.adapter.OnItemClickListener;
import luque.david.androidchat.payments.adapter.PaymentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements PaymentView, OnItemClickListener {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private PaymentPresenter presenter;
    private PaymentAdapter adapter;

    public PaymentFragment() {
        // Required empty public constructor
        presenter = new PaymentPresenterImpl(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deals_list, container, false);
        ButterKnife.bind(this, view);

        setupAdapter();
        setupRecyclerView();
        presenter.onCreate();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        ArrayList<Deal> deals = new ArrayList<Deal>();
        adapter = new PaymentAdapter(deals, this);
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

    @Override
    public void onPaymentAdded(Deal deal) {
        adapter.add(deal);
    }

    @Override
    public void onPaymentUpdated(Deal deal) {
        adapter.update(deal);
    }

    @Override
    public void onPaymentRemoved(Deal deal) {
        adapter.delete(deal);
    }

    @Override
    public void onItemClick(Deal deal) {

    }

    @Override
    public void onItemLongClick(Deal deal) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

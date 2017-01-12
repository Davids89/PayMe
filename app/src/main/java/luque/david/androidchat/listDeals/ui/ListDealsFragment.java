package luque.david.androidchat.listDeals.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.androidchat.R;
import luque.david.androidchat.listDeals.ListDealsPresenter;
import luque.david.androidchat.listDeals.ListDealsPresenterImpl;
import luque.david.androidchat.listDeals.adapter.DealsListAdapter;
import luque.david.androidchat.listDeals.adapter.OnItemClickListener;
import luque.david.androidchat.entities.Deal;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDealsFragment extends Fragment implements OnItemClickListener, ListDealsView {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListDealsPresenter presenter;
    private DealsListAdapter adapter;

    public ListDealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deals_list, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        setupRecyclerView();

        presenter = new ListDealsPresenterImpl(this);
        presenter.onCreate();
        return view;
    }

    private void setupAdapter() {

        ArrayList<Deal> deals = new ArrayList<Deal>();

        adapter = new DealsListAdapter(deals, this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        adapter.clear();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        adapter.clear();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(Deal deal) {

    }

    @Override
    public void onItemLongClick(Deal deal) {

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
}

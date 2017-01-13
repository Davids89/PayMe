package luque.david.androidchat.listDeals.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import luque.david.androidchat.R;
import luque.david.androidchat.entities.Deal;

/**
 * Created by david on 28/10/16.
 */

public class DealsListAdapter extends RecyclerView.Adapter<DealsListAdapter.ViewHolder> {

    private List<Deal> deals;
    private OnItemClickListener onItemClickListener;

    public DealsListAdapter(List<Deal> deals, OnItemClickListener onItemClickListener) {
        this.deals = deals;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Deal deal = deals.get(position);
        String name = deal.getName();
        String amount = deal.getAmount();
        String info = deal.getInfo();

        holder.dealName.setText(name);
        holder.dealAmount.setText(amount);
        holder.txtInfo.setText(info);
    }

    public void add(Deal deal) {
        if (!deals.contains(deal)) {
            deals.add(deal);
            notifyDataSetChanged();
        }
    }

    public void update(Deal deal) {
        for(int i = 0; i < deals.size(); i++){
            if(deals.get(i).getDealId().equals(deal.getDealId())){
                deals.set(i, deal);
                notifyDataSetChanged();
            }
        }
    }

    public void delete(Deal deal) {
        for(int i = 0; i < deals.size(); i++){
            if(deals.get(i).equals(deal)){
                deals.remove(deal);
                notifyDataSetChanged();
            }
        }
    }

    public void clear() {
        deals = null;
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.deal_name)
        TextView dealName;
        @Bind(R.id.deal_amount)
        TextView dealAmount;
        @Bind(R.id.txtInfo)
        TextView txtInfo;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final Deal deal, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(deal);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(deal);
                    return false;
                }
            });
        }
    }
}

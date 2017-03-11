package luque.david.payme.dealDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import luque.david.payme.R;
import luque.david.payme.entities.Friend;

/**
 * Created by david on 11/3/17.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<Friend> mFriends;

    public FriendAdapter(List<Friend> mFriends) {
        this.mFriends = mFriends;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.friendEmail.setText(mFriends.get(position).getEmail());

    }

    public void setupFriendList(List<Friend> friends){
        this.mFriends = friends;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.friendImage)
        CircleImageView friendImage;
        @Bind(R.id.friendEmail)
        TextView friendEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.spotifywrapped.friendslist;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.example.spotifywrapped.R;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    private List<Friend> friendList; //friend Objects
    private boolean viewingAddedFriends = false;
    private List<Friend> filteredFriendList;

    /**
     * FriendListAdapter constructor
     * List is the datasource for the RecyclerView
     * @param friendList
     */
    public FriendListAdapter(List<Friend> friendList) {
        this.friendList = friendList;
    }

    /**
     * Inflates the item layout and creates a ViewHolder to hold it
     * Called by RecyclerView when it needs a new ViewHolder to display an item with
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return new instance of ViewHolder inner class
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_friend_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds data to the view holder
     * retrieves the friend information at the specified position from the 'friendList'
     * and sets this information to the provided ViewHolder
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friendInfo = friendList.get(holder.getAdapterPosition());
        holder.textView.setText(friendInfo.getFriend());
        updateButton(holder.addFriendButton, friendInfo.isAdded());

        holder.addFriendButton.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                Friend currentFriend = friendList.get(currentPosition);
                currentFriend.setAdded(!currentFriend.isAdded());
                updateButton(holder.addFriendButton, currentFriend.isAdded());
            }
        });
    }

    private void updateButton(Button button, boolean isAdded) {
        button.setText(isAdded ? "Following" : "Follow");
    }

    /**
     * @return ?Number of items in data held by adapter
     */
    public int getItemCount() {
        return friendList.size();
    }

    /**
     * holds references to the UI components within each item of the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button addFriendButton;

        /**
         * constructor takes a view object and finds the TextView within it
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            addFriendButton = view.findViewById(R.id.addFriendButton);
        }
    }
}

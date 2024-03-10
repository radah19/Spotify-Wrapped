package com.example.spotifywrapped;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    private List<String> friendList; //example data type

    /**
     * FriendListAdapter constructor
     * List is the datasource for the RecyclerView
     * @param friendList
     */
    public FriendListAdapter(List<String> friendList) {
        this.friendList = friendList;
    }

    /**
     * Inflates the item layout and creates a ViewHolder to hold it
     * Called by RecyclerView when it needs a new ViewHolder to display an item with
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return new instance of ViewHolder inner class
     */
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list, parent, false);
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
        String friendInfo = friendList.get(position);
        holder.textView.setText(friendInfo);
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

        /**
         * constructor takes a view object and finds the TextView within it
         * @param view 
         */
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }
}

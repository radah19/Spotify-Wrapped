package com.example.spotifywrapped.spotifywrappedlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.spotifywrapped.R;
import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private final List<String> mFriends;

    public FriendsAdapter(List<String> friends) {
        this.mFriends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String friend = mFriends.get(position);
        holder.friendCheckBox.setText(friend);
        holder.friendCheckBox.setOnCheckedChangeListener(null);
        holder.friendCheckBox.setChecked(false); // Or handle saved states here if needed
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox friendCheckBox;

        public ViewHolder(View view) {
            super(view);
            friendCheckBox = view.findViewById(R.id.friendCheckBox);
        }
    }
}
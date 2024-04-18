package com.example.spotifywrapped.friendslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.navbar.NavbarClass;

public class FriendListActivity extends AppCompatActivity {
    private FriendListAdapter adapter;
    private List<Friend> filteredFriendList;
    private List<Friend> friendList;
    private boolean searchStarted = false;
    /**
     * called when activity is created
     * @param savedInstanceState saved data
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        RecyclerView recyclerView = findViewById(R.id.friendsListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        friendList = new ArrayList<>();
        friendList.add(new Friend("Friend 1"));
        friendList.add(new Friend("Friend 2"));
        friendList.add(new Friend("Friend 3"));
        friendList.add(new Friend("Friend 4"));
        friendList.add(new Friend("Friend 5"));

        filteredFriendList = new ArrayList<>();

        adapter = new FriendListAdapter(filteredFriendList);
        recyclerView.setAdapter(adapter);

        NavbarClass.initializeNavbar(this);
        setUpSearchView();
    }

    private void setUpSearchView() {
        final SearchView searchView = findViewById(R.id.friendsListSearchView);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchStarted = false;
                    searchView.post(() ->searchView.clearFocus());
                    filter("");
                } else {
                    if(!searchStarted) {
                        searchStarted = true;
                    }
                    filter(newText);
                }
                return true;
            }
        });
    }
    private void filter(String text) {
        filteredFriendList.clear();
        if (!searchStarted || text.isEmpty()) {
            for (Friend friend : friendList) {
                if (friend.isAdded()) {
                    filteredFriendList.add(friend);
                }
            }
        } else {
            text = text.toLowerCase();
            for (Friend friend : friendList) {
                if (friend.getFriend().toLowerCase().contains(text)) {
                    filteredFriendList.add(friend);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

}

package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class FriendListActivity extends AppCompatActivity {
    /**
     * called when activity is created
     * @param savedInstanceState saved data
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);

        RecyclerView recyclerView = findViewById(R.id.friendsListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> friendList = new ArrayList<>();

        FriendListAdapter adapter = new FriendListAdapter(friendList);
        recyclerView.setAdapter(adapter);
    }
}

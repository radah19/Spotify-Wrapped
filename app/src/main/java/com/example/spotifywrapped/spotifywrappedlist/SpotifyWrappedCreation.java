package com.example.spotifywrapped.spotifywrappedlist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.spotifywrapped.R;

public class SpotifyWrappedCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spotify_wrapped_creation);

        // Initialize the AutoCompleteTextView
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        AutoCompleteTextView autoCompleteTextViewFriends = findViewById(R.id.autoCompleteTextViewFriends);

        // Define the options for the dropdown
        String[] timeRanges = {"Long Term", "Medium Term", "Short Term"};
        String[] friends = {"Friend 1", "Friend 2", "Friend 3"};

        // Create an ArrayAdapter using the string array and a default layout for the dropdown items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, timeRanges);

        ArrayAdapter<String> adapterFriends = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, friends);

        // Set the adapter to the AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextViewFriends.setAdapter(adapterFriends);

        // dropdown to appear as soon as the AutoCompleteTextView is focused
        // or when tapped

        autoCompleteTextView.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) autoCompleteTextView.showDropDown();
        });
        autoCompleteTextView.setOnClickListener(v -> autoCompleteTextView.showDropDown());

        autoCompleteTextViewFriends.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) autoCompleteTextViewFriends.showDropDown();
        });
        autoCompleteTextViewFriends.setOnClickListener(v -> autoCompleteTextViewFriends.showDropDown());
    }
}
    //@Override
/*    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spotify_wrapped_creation);

        // Initialize the Spinner
        Spinner spinnerTimeRange = findViewById(R.id.spinner_time_range);

        Spinner spinnerFriends = findViewById(R.id.spinner_select_friends);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_range_options, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> friendsAdapter = ArrayAdapter.createFromResource(this,
                R.array.friend_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerTimeRange.setAdapter(adapter);

        spinnerFriends.setAdapter(friendsAdapter);

        // Set an item selected listener for the spinner (optional)
        spinnerTimeRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. Retrieve the selected item using parent.getItemAtPosition(pos)
                // Example: String selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinnerFriends.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. Retrieve the selected item using parent.getItemAtPosition(pos)
                // Example: String selected = parent.getItemAtPosition(position).toString();
                // Handle friend selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection
            }
        });*/

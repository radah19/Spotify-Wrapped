package com.example.spotifywrapped;

public class user {
    private final String username;
    private final String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter methods for username and password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

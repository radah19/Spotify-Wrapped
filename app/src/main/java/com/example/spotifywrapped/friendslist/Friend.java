package com.example.spotifywrapped.friendslist;

public class Friend {
    private String friend;
    private boolean isAdded;
    private boolean showFollowButton = true;

    public Friend (String friend) {
        this.friend = friend;
        this.isAdded = false;
    }
    public String getFriend() {
        return friend;
    }
    public void setFriend(String friend) {
        this.friend = friend;
    }
    public boolean isAdded() {
        return isAdded;
    }
    public void setAdded(boolean added) {
        isAdded = added;
    }
    public boolean shouldShowFollowButton() {
        return showFollowButton;
    }
    public void setShowFollowButton(boolean showFollowButton) {
        this.showFollowButton = showFollowButton;
    }
}

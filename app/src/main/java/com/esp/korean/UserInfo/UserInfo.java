package com.esp.korean.UserInfo;

import android.net.Uri;

public class UserInfo {

    public String userName;
    public String avatarUrl;
    public String userJob;
    public String userCoverUrl;
    public int numFollowers;
    public int numFollowing;
    public String description;

    public UserInfo() {
    }

    public UserInfo(String userName, String avatarUrl) {
        this(userName, avatarUrl, "Unknow", null, 0, 0, "No description");
    }

    public UserInfo(String userName, String avatarUrl, String userJob, String userCoverUrl, int numFollowers, int numFollowing, String description) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.userJob = userJob;
        this.userCoverUrl = userCoverUrl;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String getUserCoverUrl() {
        return userCoverUrl;
    }

    public void setUserCoverUrl(String userCoverUrl) {
        this.userCoverUrl = userCoverUrl;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    public int getNumFollowing() {
        return numFollowing;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

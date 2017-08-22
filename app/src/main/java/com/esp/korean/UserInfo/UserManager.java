package com.esp.korean.UserInfo;

public class UserManager {

    public static UserInfo userInfo;

    public static void updateUserInfo() {
        new UpdateUserInfo(userInfo).execute();
    }

}

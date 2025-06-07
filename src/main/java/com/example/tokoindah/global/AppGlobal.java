package com.example.tokoindah.global;

import com.example.tokoindah.model.User;

public class AppGlobal {
    public static User user;
    public static void setLoggedInUser(User user) {
        AppGlobal.user = user;
    }
    public static void clearLoggedInUser() {
        AppGlobal.user = null;
    }
}

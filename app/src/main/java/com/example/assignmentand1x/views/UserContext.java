package com.example.assignmentand1x.views;


import com.example.assignmentand1x.model.Account;

class UserContext {
    private static Account loggedUser;

    private UserContext() {
    }

    public static void login(Account user)
    {
        loggedUser = user;
    }
    public static void logout()
    {
        loggedUser = null;
    }
    public static boolean isLogged()
    {
        return loggedUser != null;
    }
    public static int getLoggedUserId()
    {
        return isLogged() ? loggedUser.getAccountId(): -1;
    }
}

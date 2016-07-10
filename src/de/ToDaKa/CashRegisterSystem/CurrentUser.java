package de.ToDaKa.CashRegisterSystem;

/**
 * Created by Daniel on 07.07.2016.
 */
public class CurrentUser {
    private static int CurrentUserID;

    public static int getCurrentUserID() {
        return CurrentUserID;
    }

    public static void setCurrentUserID(int currentUserID) {
        CurrentUserID = currentUserID;
    }
    public static void reset()
    {
        CurrentUserID=-1;
    }
    public static boolean isAdmin()
    {
        return true;
    }
}

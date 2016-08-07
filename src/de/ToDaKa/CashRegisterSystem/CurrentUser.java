package de.ToDaKa.CashRegisterSystem;

/**
 * Created by Daniel on 07.07.2016.
 */
public class CurrentUser {
    private static long CurrentUserID;

    public static long getCurrentUserID() {
        return CurrentUserID;
    }

    public static void setCurrentUserID(long currentUserID) {
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

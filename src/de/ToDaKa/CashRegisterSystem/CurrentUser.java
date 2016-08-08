package de.ToDaKa.CashRegisterSystem;

/**
 Class: CurrentUser
 @author Daniel Albrecht

 Save a UserID, so you always know the current User.
 If no user has been set, ther current User ID is -1
 **/
public class CurrentUser {
    private static long CurrentUserID=-1;

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
    /**
     Funktion: isAdmin
     @author Daniel Albrecht
     @return boolean

     Can find out if the current User is a admin
     **/
    public static boolean isAdmin()
    {
        return Main.CRS.findCashier(CurrentUserID).isAdmin();
    }
}

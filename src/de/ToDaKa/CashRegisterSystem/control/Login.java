package de.ToDaKa.CashRegisterSystem.control;

import de.ToDaKa.CashRegisterSystem.model.CashRegisterSystem;
import de.ToDaKa.CashRegisterSystem.model.Cashier;
import de.ToDaKa.CashRegisterSystem.storage.core.DataController;
import de.ToDaKa.CashRegisterSystem.storage.core.IGenericDao;

import static de.ToDaKa.CashRegisterSystem.MD5.getMD5;

/**
 Class: EmployeeAdvancedTableViewScreen
 @author Tobias Rieß


 **/
public class Login
{
    public static int login(CashRegisterSystem _CRS, String _ID, String _pw)
    {
        int ret = -1; //-1 wrong login data, 0 Admin, 1 Normal User

        String MD5pw = getMD5(_pw);

        Cashier C = _CRS.getCashierList().get(Integer.parseInt(_ID));

        String CashierMD5PW = C.getMd5Password();

        if(CashierMD5PW.compareTo(MD5pw) == 0)
        {
            if(C.isAdmin())
            {
                ret = 0;
            }
            else
            {
                ret = 1;
            }
        }

        return ret;
    }
}

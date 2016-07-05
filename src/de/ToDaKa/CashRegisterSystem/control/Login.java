package de.ToDaKa.CashRegisterSystem.control;

import de.ToDaKa.CashRegisterSystem.model.Cashier;
import de.ToDaKa.CashRegisterSystem.storage.core.DataController;
import de.ToDaKa.CashRegisterSystem.storage.core.IGenericDao;

import static de.ToDaKa.CashRegisterSystem.MD5.getMD5;

/**
 * Created by Tobias on 27.06.2016.
 */
public class Login
{
    public int Login(String _ID, String _pw)
    {
        int ret = -1; //-1 wrong login data, 0 Admin, 1 Normal User

        String MD5pw = getMD5(_pw);

        IGenericDao<Cashier> CashierDao = DataController.getInstance().getCashierDao();

        Cashier C= CashierDao.findById(new Long(_ID));

        if(C.getMd5Password() == MD5pw)
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

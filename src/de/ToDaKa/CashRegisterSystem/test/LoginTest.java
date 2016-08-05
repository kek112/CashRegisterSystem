package de.ToDaKa.CashRegisterSystem.test;

import de.ToDaKa.CashRegisterSystem.DataProvider;
import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.storage.IStorageController;
import de.ToDaKa.CashRegisterSystem.storage.JpaStorageController;
import de.ToDaKa.CashRegisterSystem.storage.core.DataController;
import de.ToDaKa.CashRegisterSystem.storage.core.IGenericDao;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;
import org.junit.Test;
import static de.ToDaKa.CashRegisterSystem.control.Login.login;

import static org.junit.Assert.*;

/**
 * Created by Tobias on 05.08.2016.
 */
public class LoginTest {


    public CashRegisterSystem CRS;

    @org.junit.Before
    public void setUp() throws Exception
    {
        //Create Test CRS
        DataProvider dataProvider =new DataProvider();
        CRS=dataProvider.creatTestCRS();

        IStorageController sc = new JpaStorageController();

        try
        {
            sc.saveCashRegisterSystem(CRS);
        }
        catch (StorageException e)
        {
            e.printStackTrace();
        }
        //search by ID
        IGenericDao<Customer> CustomerDao = DataController.getInstance().getCustomerDao();
        IGenericDao<Cashier> CashierDao = DataController.getInstance().getCashierDao();

    }

    @org.junit.Test
    public void testLogin() throws Exception {
        //Nutzer = Daniel Albrecht Nutzer ID = 0 pw = geheim    admin = ja
        //Nutzer = Karl-Erik Kley  Nutzer ID = 1 pw = Passlord  admin = nein

        String DanielID = "0";
        String DanielPW = "geheim";

        int DanielResult = 0;

        String KarlID = "1";
        String KarlPW = "Passlord";

        int KarlResult = 1;

        String FalsePW = "UnitTest";

        int FalseResult = -1;

        assertEquals(DanielResult, login(CRS, DanielID, DanielPW));
        assertEquals(KarlResult, login(CRS, KarlID, KarlPW));
        assertEquals(FalseResult, login(CRS, DanielID, FalsePW));

    }
}
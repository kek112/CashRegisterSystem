package de.ToDaKa.CashRegisterSystem.test;

import de.ToDaKa.CashRegisterSystem.DataProvider;
import de.ToDaKa.CashRegisterSystem.control.Statistic;
import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.storage.IStorageController;
import de.ToDaKa.CashRegisterSystem.storage.JpaStorageController;
import de.ToDaKa.CashRegisterSystem.storage.core.DataController;
import de.ToDaKa.CashRegisterSystem.storage.core.IGenericDao;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;

import java.util.Date;

import static de.ToDaKa.CashRegisterSystem.control.Statistic.statistic;
import static org.junit.Assert.*;

/**
 * Created by Tobias on 05.08.2016.
 */
public class StatisticTest {

    public CashRegisterSystem CRS;

    @org.junit.Before
    public void setUp() throws Exception
    {

        //Create Test CRS
        DataProvider dataProvider =new DataProvider();
        CRS=dataProvider.creatTestCRS();

        CRS.addBon(new Bon(CRS.getCashierList().get(0), CRS.getCashRegisterList().get(0)));
        CRS.getBon().get(0).addBonInventory(new BonInventory(2, CRS.getBon().get(0), CRS.getInventoryList().get(0)));

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
    public void testStatistic() throws Exception {
        //2*1.99 (Knusper Flocken) = 2.98;

        float result = 2.98f;

        assertEquals(result, statistic(CRS, new Date(2000, 1, 1, 0, 0)));
    }
}
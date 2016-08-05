package de.ToDaKa.CashRegisterSystem;

import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.model.execptions.*;

import java.util.Date;

/**
 * Created by Daniel on 01.07.2016.
 */
public class  DataProvider {

    public static CashRegisterSystem creatTestCRS()
    {
        CashRegisterSystem CRS= new CashRegisterSystem();

        try
    {
        CRS.addCustomer(new Customer("Stefan", "Dietrich", true, 263275154,new Date(1990,6,9)));
        CRS.addCustomer(new Customer("Anke", "Loew", false, 361917187,new Date(1962,3,28)));
        CRS.addCustomer(new Customer("Tobias", "Rothschild", true, 611231427,new Date(1961,6,10)));
        CRS.addCustomer(new Customer("Matthias", "Bumgarner", true, 442595919, new Date(1938,7,18)));

        }
        catch (CustomerExistsException e)
        {
            e.printStackTrace();

        }
        try
        {

            CRS.addInventory(new Inventory(Long.parseLong("4012362044208"),"Knusper Flocken",4,1.99f, true));
            CRS.addInventory(new Inventory(Long.parseLong("4062400115483"),"SIERRA Tequila Silver",38,13.99f, true));
            CRS.addInventory(new Inventory(Long.parseLong("4014348916158"),"GLYSANTIN G48",14,14.99f, false));
            CRS.addInventory(new Inventory(Long.parseLong("4008400204727"),"Kinder Schokolade",16,0.99f, true));
            CRS.addInventory(new Inventory(Long.parseLong("3661075047818"),"Feuerzeug Lila",53,0.50f, false));
            CRS.addInventory(new Inventory(Long.parseLong("9783808536261"),"Informationstechnik, Neue Netze",27,33.5f, false));
            CRS.addInventory(new Inventory(Long.parseLong("4104640015014"),"Förstina Sprudel Naturell",45,0.39f, true));
            CRS.addInventory(new Inventory(Long.parseLong("4250542847472"),"Collegeblock 80 Blatt",23,0.99f, false));



        }
        catch (InventoryExistsException e)
        {
            e.printStackTrace();

        }

        try
        {
            CRS.addCashier(new Cashier("Daniel", "Albrecht", MD5.getMD5("geheim") ,true));
            CRS.addCashier(new Cashier("Karl-Erik", "Kley", MD5.getMD5("Passlord") ,false));
            CRS.addCashier(new Cashier("Tobias", "Rieß", MD5.getMD5("Test") ,true));


        }
        catch (CashierExistsException e)
        {
            e.printStackTrace();

        }
        try
        {
            CRS.addCashRegister(new CashRegister());
        }
        catch (CashRegisterExistsException e)
        {
            e.printStackTrace();

        }
        return CRS;

    }
}

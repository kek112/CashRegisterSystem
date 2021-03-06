package de.ToDaKa.CashRegisterSystem;

import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.model.execptions.*;

import java.text.ParseException;
import java.util.Date;

/**
Class: DataProvider
@author Daniel Albrecht

DataProvider generate example Data

 **/
public class  DataProvider {

    /**
    Funktion: createTestCRS
    @author Daniel Albrecht
    @return CashRegisterSystem

    generate Customer, Inventory, Cashier example data and save it in a CashRegisterSystem

    **/

    public static CashRegisterSystem creatTestCRS()
    {
        CashRegisterSystem CRS= new CashRegisterSystem();

        try
    {
        try {
            CRS.addCustomer(new Customer("Stefan", "Dietrich", true, 263275154,Main.dateFormat.parse("06.09.1990")));
            CRS.addCustomer(new Customer("Anke", "Loew", false, 361917187,Main.dateFormat.parse("28.06.1962")));
            CRS.addCustomer(new Customer("Tobias", "Rothschild", true, 611231427,Main.dateFormat.parse("10.06.1961")));
            CRS.addCustomer(new Customer("Matthias", "Bumgarner", true, 442595919,Main.dateFormat.parse("18.07.1938")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            CRS.addInventory(new Inventory(Long.parseLong("3661075247818"),"Informationstechnik, Neue Netze",27,33.5f, false));
            CRS.addInventory(new Inventory(Long.parseLong("4104640015014"),"Förstina Sprudel Naturell",45,0.39f, true));
            CRS.addInventory(new Inventory(Long.parseLong("4250542847472"),"Collegeblock 80 Blatt",23,0.99f, false));



        }
        catch (InventoryExistsException e)
        {
            e.printStackTrace();

        }

        try
        {
            CRS.addCashier(new Cashier("Daniel", "Albrecht",Long.parseLong("01765487511"), Main.dateFormat.parse("24.10.1993"), MD5.getMD5("geheim") ,true));
            CRS.addCashier(new Cashier("Karl-Erik", "Kley",Long.parseLong("2134123123"), Main.dateFormat.parse("21.02.1994"), MD5.getMD5("Passlord") ,false));
            CRS.addCashier(new Cashier("Tobias", "Rieß",Long.parseLong("017544775456"), Main.dateFormat.parse("02.08.1992"), MD5.getMD5("Test") ,true));


        }
        catch (CashierExistsException e)
        {
            e.printStackTrace();

        } catch (ParseException e) {
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

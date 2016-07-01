package de.ToDaKa.CashRegisterSystem;

import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.model.execptions.*;
import de.ToDaKa.CashRegisterSystem.storage.*;
import de.ToDaKa.CashRegisterSystem.storage.core.*;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;


public class MainClass
{
    public static void main(String[] args)
    {
        /**
        // test main

        //Create Cashier and Position
        Position Admin=new Position("Hauptkassierer");
        Position User=new Position("Kassierer");

        Cashier CurrentCashier=new Cashier("Daniel", "Albrecht", Admin);

        //Create Cash Register and Location
       CashRegister CurrentCashRegister =new CashRegister();


        //Create Bon
        Bon CurrentBon=new Bon(new Date(),CurrentCashier, CurrentCashRegister);


        //Create a Article in the Inventory
        Inventory CurrentArticle=new Inventory(123426789,"Laptop", 5,299.99f, false);

        //Add Article
        new BonInventory(2,new Date(),CurrentArticle.getPrice(),CurrentBon, CurrentArticle);

        CurrentArticle=new Inventory(123456789,"Tastatur", 2,19.99f, false);
        new BonInventory(2,new Date(),CurrentBon, CurrentArticle);

        //Save in DB
        EntityManagerFactory EMF;
        EntityManager EM;
        EMF=Persistence.createEntityManagerFactory("CashRegisterSystem-Unit");
        EM=EMF.createEntityManager();

        EM.getTransaction().begin();
        EM.persist(CurrentArticle);
        EM.getTransaction();


        EM.close();
        System.out.println("Datum: "+CurrentBon.getDateTime());
        System.out.println(CurrentBon.getCashier().getPosition().getName()+": "+CurrentBon.getCashier().getLastName()+", "+CurrentBon.getCashier().getFirstName()+"\n");

        System.out.println("Artikel:");
        BonInventory CurrentBonInventory;
        for(int i=0;i<CurrentBon.getBonInventory().size();i++)
        {
            CurrentBonInventory=CurrentBon.getBonInventorys().get(i);

            System.out.print(CurrentBonInventory.getInventory().getID()+" ");
            System.out.print(CurrentBonInventory.getAmount()+" ");
            System.out.print(CurrentBonInventory.getPrice()+" ");
            System.out.print(CurrentBonInventory.getInventory().getName()+"\n");
        }
        */


        //Create Test CRS
        CashRegisterSystem CRS=new CashRegisterSystem();
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


        Customer searchedCustomer= CustomerDao.findById(new Long(1));
        System.out.println( searchedCustomer.getId() +" - "+searchedCustomer.getFirstName() );

    }
}

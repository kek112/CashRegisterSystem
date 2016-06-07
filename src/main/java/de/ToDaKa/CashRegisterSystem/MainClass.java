package de.ToDaKa.CashRegisterSystem;


import de.ToDaKa.CashRegisterSystem.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainClass
{
    public static void main(String[] args)
    {
        // Create some test data
        Inventory Article = new Inventory(12345123,"Kaffeetasse",2,1.99f, false);

        //Save in DB
        EntityManagerFactory EMF = Persistence.createEntityManagerFactory("CashRegisterSystem-Unit");
        EntityManager EM = EMF.createEntityManager();

        EM.getTransaction().begin();
        EM.persist( Article);
        EM.getTransaction().commit();

        int ID;
        ID=Article.getID();

        Inventory Article2=EM.find(Inventory.class,ID);

        System.out.println(ID+" "+Article2.getName());

    }
}

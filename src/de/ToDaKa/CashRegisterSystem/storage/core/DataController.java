package de.ToDaKa.CashRegisterSystem.storage.core;

import de.ToDaKa.CashRegisterSystem.model.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataController
{
    private static final String PERSISTENCE_UNIT_NAME = "CashRegisterSystem-Unit";

    private EntityManagerFactory entityManagerFactory;

    /*
        Singleton definition
     */
    private static DataController instance;

    public static DataController getInstance()
    {
        if( instance == null )
            instance = new DataController();

        return instance;
    }

    /*
        Private Constructor
     */

    private DataController()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
    }

    /*
        Dao Getter
     */

    public IGenericDao<Inventory> getInventoryDao()
    {
        return new JpaGenericDao<Inventory>( Inventory.class,
                this.entityManagerFactory.createEntityManager() );
    }

    public IGenericDao<Bon> getBonDao()
    {
        return new JpaGenericDao<Bon>(Bon.class,
                this.entityManagerFactory.createEntityManager() );
    }
    public IGenericDao<Cashier> getCashierDao()
    {
        return new JpaGenericDao<Cashier>(Cashier.class,
                this.entityManagerFactory.createEntityManager() );
    }
    public IGenericDao<Customer> getCustomerDao()
    {
        return new JpaGenericDao<Customer>(Customer.class,
                this.entityManagerFactory.createEntityManager() );
    }
    public IGenericDao<CashRegister> getCashRegisterDao()
    {
        return new JpaGenericDao<CashRegister>(CashRegister.class,
                this.entityManagerFactory.createEntityManager() );
    }

}

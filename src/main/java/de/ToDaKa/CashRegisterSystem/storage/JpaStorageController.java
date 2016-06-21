package de.ToDaKa.CashRegisterSystem.storage;

import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.storage.core.DataController;
import de.ToDaKa.CashRegisterSystem.storage.core.IGenericDao;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;

import java.util.ArrayList;
import java.util.Collection;

public class JpaStorageController implements IStorageController
{

    public CashRegisterSystem loadCashRegisterSystem() throws StorageException
    {
        IGenericDao<Inventory> InventoryDao = DataController.getInstance().getInventoryDao();
        IGenericDao<Cashier> CashierDao = DataController.getInstance().getCashierDao();
        IGenericDao<CashRegister> CashRegisterDao = DataController.getInstance().getCashRegisterDao();
        IGenericDao<Customer> CustomerDao = DataController.getInstance().getCustomerDao();
        IGenericDao<Bon> BonDao = DataController.getInstance().getBonDao();


        CashRegisterSystem _CashRegisterSystem=new CashRegisterSystem();


        _CashRegisterSystem.setInventoryList(new ArrayList<Inventory>(InventoryDao.findAll()));
        _CashRegisterSystem.setCashierList(new ArrayList<Cashier>(CashierDao.findAll()));
        _CashRegisterSystem.setBon(new ArrayList<Bon>(BonDao.findAll()));
        _CashRegisterSystem.setCashRegisterList(new ArrayList<CashRegister>(CashRegisterDao.findAll()));
        _CashRegisterSystem.setCustomerList(new ArrayList<Customer>(CustomerDao.findAll()));


        return _CashRegisterSystem;
    }

    public void saveCashRegisterSystem( CashRegisterSystem _CashRegisterSystem ) throws StorageException
    {
        IGenericDao<Inventory> InventoryDao = DataController.getInstance().getInventoryDao();
        IGenericDao<Cashier> CashierDao = DataController.getInstance().getCashierDao();
        IGenericDao<CashRegister> CashRegisterDao = DataController.getInstance().getCashRegisterDao();
        IGenericDao<Customer> CustomerDao = DataController.getInstance().getCustomerDao();
        IGenericDao<Bon> BonDao = DataController.getInstance().getBonDao();


        for( Inventory _inventory : _CashRegisterSystem.getInventoryList() )
        {
            if( _inventory.getId() == null )
                InventoryDao.create( _inventory );
            else
                InventoryDao.update( _inventory );
        }
        for( Cashier _Cashier : _CashRegisterSystem.getCashierList() )
        {
            if( _Cashier.getId() == null )
                CashierDao.create( _Cashier );
            else
                CashierDao.update( _Cashier );
        }
        for( CashRegister _CashRegister : _CashRegisterSystem.getCashRegisterList() )
        {
            if( _CashRegister.getId() == null )
                CashRegisterDao.create( _CashRegister );
            else
                CashRegisterDao.update( _CashRegister );
        }

        for(  Customer _Customer : _CashRegisterSystem.getCustomerList() )
        {
            if( _Customer.getId() == null )
                CustomerDao.create( _Customer );
            else
                CustomerDao.update( _Customer );
        }

        for( Bon _Bon : _CashRegisterSystem.getBon() )
        {
            if( _Bon.getId() == null )
                BonDao.create( _Bon );
            else
                BonDao.update( _Bon );
        }



    }
}

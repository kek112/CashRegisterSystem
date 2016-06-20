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

        Collection<Inventory> InventoryFromDatabase = InventoryDao.findAll();

        CashRegisterSystem _CashRegisterSystem=new CashRegisterSystem();
        _CashRegisterSystem.setInventoryList(new ArrayList<Inventory>(InventoryFromDatabase));


        return _CashRegisterSystem;
    }

    public void saveCashRegisterSystem( CashRegisterSystem _CashRegisterSystem ) throws StorageException
    {
        IGenericDao<Inventory> InventoryDao = DataController.getInstance().getInventoryDao();

        for( Inventory _inventory : _CashRegisterSystem.getInventoryList() )
        {
            if( _inventory.getId() == null )
                InventoryDao.create( _inventory );
            else
                InventoryDao.update( _inventory );
        }
    }
}

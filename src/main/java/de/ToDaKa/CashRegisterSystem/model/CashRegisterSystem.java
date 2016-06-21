package de.ToDaKa.CashRegisterSystem.model;


import de.ToDaKa.CashRegisterSystem.model.execptions.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Daniel on 15.06.2016.
 */
public class CashRegisterSystem implements Serializable {

    private static final long serialVersionUID = -4224741350801502250L;

    private List<Bon> BonList= new ArrayList<Bon>();
    private List<BonInventory> BonInventoryList=new ArrayList<BonInventory>();
    private List<Cashier> CashierList=new ArrayList<Cashier>();
    private List<CashRegister> CashRegisterList =new ArrayList<CashRegister>();
    private List<Customer> CustomerList=new ArrayList<Customer>();
    private List<Inventory> InventoryList=new ArrayList<Inventory>();

    public CashRegisterSystem()
    {

    }

    public Inventory addInventory(Inventory _Inventory) throws InventoryExistsException
    {
        Inventory existing = this.findInventory(_Inventory);
        if(existing!=null)
        {
            throw new InventoryExistsException( "Article "+_Inventory+" already exists in the Cash Register System" );

        }
        {
            InventoryList.add(_Inventory);
        }
        return _Inventory;
    }
    public Bon addBon(Bon _Bon) throws BonExistsException
    {
        Bon existing = this.findBon(_Bon);
        if(existing!=null)
        {
            throw new BonExistsException( "Bon "+_Bon+" already exists in the Cash Register System" );

        }
        {
            BonList.add(_Bon);
        }
        return _Bon;
    }

    public void addCashier(Cashier _Cashier) throws CashierExistsException
    {
        Cashier existing = this.findCashier(_Cashier);
        if(existing!=null)
        {
            throw new CashierExistsException( "Cashier "+_Cashier+" already exists in the Cash Register System" );
        }
        {
            CashierList.add(_Cashier);
        }
    }
    public CashRegister addCashRegister(CashRegister _CashRegister) throws CashRegisterExistsException
    {
        CashRegister existing = this.findCashRegister(_CashRegister);
        if(existing!=null)
        {
            throw new CashRegisterExistsException( "Cash Register "+_CashRegister+" already exists in the Cash Register System" );
        }
        {
            CashRegisterList.add(_CashRegister);
        }
        return _CashRegister;
    }

    public Customer addCustomer(Customer _Customer) throws CustomerExistsException
    {
        Customer existing = this.findCustomer(_Customer);
        if(existing!=null)
        {
            throw new CustomerExistsException( "Customer "+_Customer+" already exists in the Cash Register System" );
        }
        {
            CustomerList.add(_Customer);
        }
        return _Customer;
    }


    public Inventory findInventory(Inventory _Inventory)
    {
        int index = this.InventoryList.indexOf(_Inventory);
        if(index!=-1)
        {
            return this.InventoryList.get(index);
        }
        else
        {
            //Search whit Barcode
            return findInventoryByBarcode(_Inventory.getBarcode());
        }
    }
    public Bon findBon(Bon _Bon)
    {
        int index = this.BonList.indexOf(_Bon);
        if(index!=-1)
        {
            return this.BonList.get(index);
        }
        else
        {
            return null;
        }
    }
    public BonInventory findBonInventory(BonInventory _BonInventory)
    {
        int index = this.BonInventoryList.indexOf(_BonInventory);
        if(index!=-1)
        {
            return this.BonInventoryList.get(index);
        }
        else
        {
            return null;
        }
    }

    public Cashier findCashier(Cashier _Cashier)
    {
        int index = this.CashierList.indexOf(_Cashier);
        if(index!=-1)
        {
            return this.CashierList.get(index);
        }
        else
        {
            return null;
        }
    }
    public CashRegister findCashRegister(CashRegister _CashRegister)
    {
        int index = this.CashierList.indexOf(_CashRegister);
        if(index!=-1)
        {
            return this.CashRegisterList.get(index);
        }
        else
        {
            return null;
        }
    }
    public Customer findCustomer(Customer _Customer)
    {
        int index = this.CustomerList.indexOf(_Customer);
        if(index!=-1)
        {
            return this.CustomerList.get(index);
        }
        else
        {
            return null;
        }
    }
    public Inventory findInventoryByBarcode(long Barcode)
    {
        for(Inventory _CurrentInventory: InventoryList)
        {
            if(_CurrentInventory.getBarcode()==Barcode)
            {
                return _CurrentInventory;
            }
        }
        return null;
    }
    public List<Bon> getBon() {
        return BonList;
    }

    public void setBon(List<Bon> bon) {
        BonList = bon;
    }

    public List<BonInventory> getBonInventory() {
        return BonInventoryList;
    }

    public void setBonInventory(List<BonInventory> bonInventory) {
        BonInventoryList = bonInventory;
    }

    public List<Cashier> getCashierList() {
        return CashierList;
    }

    public void setCashierList(List<Cashier> cashierList) {
        CashierList = cashierList;
    }

    public List<CashRegister> getCashRegisterList() {
        return CashRegisterList;
    }

    public void setCashRegisterList(List<CashRegister> cashRegisterList) {
        CashRegisterList = cashRegisterList;
    }

    public List<Customer> getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        CustomerList = customerList;
    }

    public List<Inventory> getInventoryList() {
        return InventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        InventoryList = inventoryList;
    }


}

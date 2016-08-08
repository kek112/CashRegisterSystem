package de.ToDaKa.CashRegisterSystem.model;


import de.ToDaKa.CashRegisterSystem.Main;
import de.ToDaKa.CashRegisterSystem.model.execptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 Class: CashRegisterSystem
 @author Daniel Albrecht

 represents the Cash Register System entitity in the database
 **/
public class CashRegisterSystem implements Serializable {

    private static final long serialVersionUID = -4224741350801502250L;

    private List<Bon> BonList= new ArrayList<Bon>();
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
    public Bon findBon(long _BonID)
    {
        Bon currentBon=null;
        for(int i = 0; i< getBon().size(); i++)
        {
            if(getCashierList().get(i).getId().equals(_BonID))
            {
                currentBon=getBon().get(i);
                i=getCashierList().size();
            }
        }
        return currentBon;
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
            throw new CustomerExistsException( "CustomerBeans "+_Customer+" already exists in the Cash Register System" );
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
            //Search with Barcode
            for(Inventory _CurrentInventory: InventoryList)
            {
                if(_Inventory.compareTo(_CurrentInventory)==1)
                {
                    return _CurrentInventory;
                }
            }
            return null;
        }
    }

    public Inventory findInventory(long _Barcode)
    {
        Inventory _Inventory;
            //Search with Barcode
            for(Inventory _CurrentInventory: InventoryList)
            {
                if(_CurrentInventory.compareTo(_Barcode)==1)
                {
                    return _CurrentInventory;
                }
            }
            return null;

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
    public Cashier findCashier(long _CashierID)
    {
        Cashier currentCashier=null;
        for(int i = 0; i< getCashierList().size(); i++)
        {
            if(getCashierList().get(i).getId().equals(_CashierID))
            {
                currentCashier=getCashierList().get(i);
                i=getCashierList().size();
            }
        }
        return currentCashier;

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
    public Customer findCustomer(long _CustomerID)
    {
        Customer currentCustomer=null;
        for(int i = 0; i< getCustomerList().size(); i++)
        {
            if(getCustomerList().get(i).getId().equals(_CustomerID))
            {
                currentCustomer=getCustomerList().get(i);
                i=getCashierList().size();
            }
        }
        return currentCustomer;
    }
    public List<Bon> getBon() {
        return BonList;
    }

    public void setBon(List<Bon> bon) {
        BonList = bon;
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

package de.ToDaKa.CashRegisterSystem.model;

import de.ToDaKa.CashRegisterSystem.storage.core.AbstractDatabaseEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 Class: Inventory
 @author Daniel Albrecht

 represents the inventory entitity in the database
 **/
@Entity
public class Inventory extends AbstractDatabaseEntity implements Comparable<Inventory>, Serializable {

    private long m_Barcode;
    private String m_Name;
    private int m_Amount;
    private float m_Price;
    private boolean m_IsFood;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "m_Inventory")
    private List<BonInventory> m_BonInventory = new ArrayList<BonInventory>();

    public Inventory() {

    }

    public Inventory(long _Barcode, String _Name, int _Amount, float _Price, boolean _IsFood) {
        this.m_Barcode = _Barcode;
        this.m_Name = _Name;
        this.m_Amount = _Amount;
        this.m_Price = _Price;
        this.m_IsFood = _IsFood;
    }

    public void addBonInventory(BonInventory _BonInventory) {
        BonInventory existing = this.findBonInventory(_BonInventory);
        if (existing != null) {
            //TODO
        }
        {
            m_BonInventory.add(_BonInventory);
            _BonInventory.setInventory(this);
        }
    }


    public BonInventory findBonInventory(BonInventory _BonInventory) {
        int index = this.m_BonInventory.indexOf(_BonInventory);
        if (index != -1) {
            return this.m_BonInventory.get(index);
        } else {
            return null;
        }
    }

    public int compareTo(Inventory _Inventory)
    {

        if( this.getBarcode()==_Inventory.getBarcode() )
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    public int compareTo(long _Barcode)
    {

        if( this.getBarcode()==_Barcode )
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    public void setPrice(float _Price) {
        this.m_Price = _Price;
    }

    public long getBarcode() {
        return m_Barcode;
    }

    public void setBarcode(long _Barcode) {
        this.m_Barcode =_Barcode;
    }

    public String getName() {
        return m_Name;
    }

    public void setName(String _Name) {
        this.m_Name = _Name;
    }

    public int getAmount() {
        return m_Amount;
    }

    public void setAmount(int _Amount) {
        this.m_Amount =_Amount;
    }

    public boolean IsFood() {
        return m_IsFood;
    }

    public void setIsFood(boolean _IsFood) {
        this.m_IsFood =_IsFood;
    }

    public float getPrice() {
        return m_Price;
    }

    public List<BonInventory> getBonInventory() {
        return m_BonInventory;
    }

    public void setBonInventory(List<BonInventory> _BonInventory) {
        this.m_BonInventory = _BonInventory;
    }

}

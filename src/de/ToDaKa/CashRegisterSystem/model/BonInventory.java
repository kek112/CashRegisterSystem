package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
/**
 Class: BonInventory
 @author Daniel Albrecht

 represents the boninventory entitity in the database
 **/
@Entity
public class BonInventory {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_BonInventoryID;
    private int m_Amount;
    private float m_Price;
    @ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER)
    private Inventory m_Inventory;
    @ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER)
    private Bon m_Bon;

    public BonInventory() {

    }
    public BonInventory(int _Amount, float _Price, Bon _Bon, Inventory _Inventory) {
        this.m_Amount = _Amount;
        this.m_Price = _Price;
        _Bon.addBonInventory(this);
        _Inventory.addBonInventory(this);
    }
    public BonInventory(int _Amount, Bon _Bon, Inventory _Inventory) {
        this.m_Amount = _Amount;
        this.m_Price = _Inventory.getPrice();
        _Bon.addBonInventory(this);
        _Inventory.addBonInventory(this);
    }

    public int getBonInventoryID()
    {
        return m_BonInventoryID;
    }

    public int getAmount() {
        return m_Amount;
    }

    public void setAmount(int _Amount) {
        this.m_Amount = _Amount;
    }

    public float getPrice() {
        return m_Price;
    }

    public void setPrice(float _Price) {
        this.m_Price = _Price;
    }

    public Inventory getInventory() {
        return m_Inventory;
    }

    public void setInventory(Inventory _Inventory) {
        m_Inventory=_Inventory;
    }

    public Bon getBon() {
        return m_Bon;
    }

    public void setBon(Bon _Bon) {
        m_Bon=_Bon;
    }
}

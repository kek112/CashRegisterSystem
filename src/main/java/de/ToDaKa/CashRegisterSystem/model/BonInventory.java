package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BonInventory {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_BonInventoryID;
    private int m_Amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date m_DateTime;
    private float m_Price;
    @ManyToOne(fetch = FetchType.LAZY)
    private Inventory m_Inventory;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bon m_Bon;

    public BonInventory() {

    }
    public BonInventory(int _Amount, Date _DateTime, float _Price, Bon _Bon, Inventory _Inventory) {
        this.m_Amount = _Amount;
        this.m_DateTime = _DateTime;
        this.m_Price = _Price;
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

    public Date getDate() {
        return m_DateTime;
    }

    public void setDate(Date _DateTime) {
        this.m_DateTime = _DateTime;
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

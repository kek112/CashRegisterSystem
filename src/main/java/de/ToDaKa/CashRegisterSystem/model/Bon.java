package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Bon {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_BonID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date m_DateTime;
    @OneToMany(mappedBy = "m_Bon")
    private List<BonInventory> m_BonInventory=new ArrayList<BonInventory>();
    @ManyToOne(fetch = FetchType.LAZY)
    private CashRegister m_CashRegister;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cashier m_Cashier;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer m_Customer;

    public Bon()
    {

    }
    public Bon(Date _DateTime, Cashier _Cashier, CashRegister _CashRegister)
    {
        this.m_DateTime = _DateTime;
        _Cashier.addBon(this);
        _CashRegister.addBon(this);
        this.m_CashRegister = _CashRegister;
    }
    public void addBonInventory(BonInventory _BonInventory)
    {
        BonInventory existing = this.findBonInventory(_BonInventory);
        if(existing!=null)
        {
            //TODO
        }
        else
        {
            m_BonInventory.add(_BonInventory);
            _BonInventory.setBon(this);
        }
        }

    public BonInventory findBonInventory(BonInventory _BonInventory)
    {
        int index = this.m_BonInventory.indexOf(_BonInventory);
        if(index!=-1)
        {
            return this.m_BonInventory.get(index);
        }
        else
        {
            return null;
        }
    }

    public List<BonInventory> getBonInventorys()
    {
        return this.m_BonInventory;
    }

    public int getBonID() {
        return m_BonID;
    }

    public Date getDateTime() {
        return m_DateTime;
    }

    public void setDateTime(Date _DateTime) {
        this.m_DateTime = _DateTime;
    }

    public Cashier getCashier() { return m_Cashier; }

    public void setCashier(Cashier _Cashier) {
        m_Cashier=_Cashier;
    }

    public CashRegister getCashRegister() { return m_CashRegister; }

    public void setCashRegister(CashRegister _CashRegister) {m_CashRegister=_CashRegister; }

    public Customer getCustomer() {
        return m_Customer;
    }

    public void setCustomer(Customer _Customer) {
        m_Customer=_Customer;
    }

    public List<BonInventory> getBonInventory() {
        return m_BonInventory;
    }

    public void setBonInventory(List<BonInventory> BonInventory) {
        this.m_BonInventory = BonInventory;
    }
}

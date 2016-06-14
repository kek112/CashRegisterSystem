package de.ToDaKa.CashRegisterSystem.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_LocationID;
    private String m_Name;
    @OneToMany(mappedBy = "m_Location")
    private List<CashRegister> m_CashRegister=new ArrayList<CashRegister>();

    public Location() {
    }

    public Location(String m_Name) {
        this.m_Name = m_Name;
    }
    public void addCashRegister(CashRegister _CashRegister)
    {
        CashRegister existing = this.findCashRegister(_CashRegister);
        if(existing!=null)
        {
            //TODO
        }
        else
        {
            m_CashRegister.add(_CashRegister);
            _CashRegister.setLocation(this);
        }
    }
    public CashRegister findCashRegister(CashRegister _CashRegister)
    {
        int index = this.m_CashRegister.indexOf(_CashRegister);
        if(index!=-1)
        {
            return this.m_CashRegister.get(index);
        }
        else
        {
            return null;
        }
    }

    public int getLocationID() {
        return m_LocationID;
    }

    public String getName() {
        return m_Name;
    }

    public void setName(String Name) {
        this.m_Name = Name;
    }

    public List<CashRegister> getCashRegister() {
        return m_CashRegister;
    }

    public void setCashRegister(List<CashRegister> CashRegister) {
        this.m_CashRegister = CashRegister;
    }
}

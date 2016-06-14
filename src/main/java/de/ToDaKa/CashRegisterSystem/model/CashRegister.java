package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CashRegister {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_CashregisterID;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location m_Location;
    @OneToMany(mappedBy = "m_CashRegister")
    private List<Bon> m_Bon=new ArrayList<Bon>();

    public CashRegister() {
    }


    public CashRegister(Location _Location) {
        _Location.addCashRegister(this);
    }

    public void addBon(Bon _Bon)
    {
        Bon existing = this.findBon(_Bon);
        if(existing!=null)
        {
            //TODO
        }
        else{
            m_Bon.add(_Bon);
            _Bon.setCashRegister(this);
        }
    }


    public Bon findBon(Bon _Bon)
    {
        int index = this.m_Bon.indexOf(_Bon);
        if(index!=-1)
        {
            return this.m_Bon.get(index);
        }
        else
        {
            return null;
        }
    }

    public List<Bon> getBon() {
        return m_Bon;
    }

    public void setBon(List<Bon> _Bon) {
        this.m_Bon = _Bon;
    }

    public Location getLocation() {
        return m_Location;
    }

    public void setLocation(Location _Location) {
        this.m_Location = _Location;
    }

    public int getCashregisterID() {
        return m_CashregisterID;
    }

    public void setCashregisterID(int _CashregisterID) {
        this.m_CashregisterID = _CashregisterID;
    }
}

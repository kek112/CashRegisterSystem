package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_PositionID;
    private String m_Name;
    @OneToMany(mappedBy = "m_Position")
    private List<Cashier> m_Cashier=new ArrayList<Cashier>();

    public Position() {
    }

    public Position(String _Name) {
        this.m_Name = _Name;
    }
    public void addCashier(Cashier _Cashier)
    {
        Cashier existing = this.findCashier(_Cashier);
        if(existing!=null)
        {
            //TODO
        }
        m_Cashier.add(_Cashier);
        _Cashier.setPosition(this);
    }


    public Cashier findCashier(Cashier _Cashier)
    {

        int index = this.m_Cashier.indexOf(_Cashier);
        if(index!=-1)
        {
            return this.m_Cashier.get(index);
        }
        else
        {
            return null;
        }
    }

    public int getPositionID() {
        return m_PositionID;
    }

    public String getName() {
        return m_Name;
    }

    public void set_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public List<Cashier> getCashier() {
        return m_Cashier;
    }

    public void setCashier(List<Cashier> _Cashier) {
        this.m_Cashier = _Cashier;
    }
}

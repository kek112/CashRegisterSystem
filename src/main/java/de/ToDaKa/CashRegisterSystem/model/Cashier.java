package de.ToDaKa.CashRegisterSystem.model;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cashier {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_Cashier;
    private String m_FirstName;
    private String m_LastName;
    @OneToMany(mappedBy = "m_Cashier")
    private List<Bon> m_Bon=new ArrayList<Bon>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Position m_Position;

    public Cashier() {
    }

    public Cashier(String _FirstName, String _LastName, Position _Position) {
        this.m_FirstName = _FirstName;
        this.m_LastName = _LastName;
        _Position.addCashier(this);
    }

    public Position getPosition() {
        return m_Position;
    }
    public void addBon(Bon _Bon)
    {

        Bon existing = this.findBon(_Bon);

        if(existing!=null)
        {
        }
        else
        {
            m_Bon.add(_Bon);
            _Bon.setCashier(this);
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

    public void setPosition(Position _Position) {
        m_Position=_Position;
    }

    public int getCashier() {
        return m_Cashier;
    }

    public void setCashier(int _Cashier) {
        this.m_Cashier = _Cashier;
    }

    public String getFirstName() {
        return m_FirstName;
    }

    public void setFirstName(String _FirstName) {
        this.m_FirstName = _FirstName;
    }

    public String getLastName() {
        return m_LastName;
    }

    public void setLastName(String _LastName) {
        this.m_LastName = _LastName;
    }
}

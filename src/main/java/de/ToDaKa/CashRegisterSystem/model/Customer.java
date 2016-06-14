package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_CustomerID;
    private String m_FirstName;
    private String m_LastName;
    @OneToMany(mappedBy = "m_Customer")
    private List<Bon> m_Bon;

    public Customer() {
    }

    public Customer(String _FirstName, String _LastName) {
        this.m_FirstName = _FirstName;
        this.m_LastName = _LastName;
    }
    public void addBon(Bon _Bon)
    {
        Bon existing = this.findBon(_Bon);
        if(existing!=null)
        {
            //TODO
        }
        m_Bon.add(_Bon);
        _Bon.setCustomer(this);
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

    public int getCustomerID() {
        return m_CustomerID;
    }

    public void setCustomerID(int _CustomerID) {
        this.m_CustomerID = _CustomerID;
    }

    public List<Bon> getBon() {
        return m_Bon;
    }

    public void setBon(List<Bon> _Bon) {
        this.m_Bon = _Bon;
    }

    public String getLastName() {
        return m_LastName;
    }

    public void setLastName(String _LastName) {
        this.m_LastName = _LastName;
    }

    public String getFirstName() {
        return m_FirstName;
    }

    public void setFirstName(String _FirstName) {
        this.m_FirstName = _FirstName;
    }
}

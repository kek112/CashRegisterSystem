package de.ToDaKa.CashRegisterSystem.model;

import de.ToDaKa.CashRegisterSystem.storage.core.AbstractDatabaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cashier extends AbstractDatabaseEntity implements Comparable<Customer>, Serializable {
    private String m_FirstName;
    private String m_LastName;
    boolean IsAdmin;
    String Md5Password;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "m_Cashier")
    private List<Bon> m_Bon=new ArrayList<Bon>();



    public Cashier() {
    }

    public Cashier(String _FirstName, String _LastName, String _Md5Password, boolean _IsAdmin) {
        this.m_FirstName = _FirstName;
        this.m_LastName = _LastName;
        this.IsAdmin=_IsAdmin;
        this.Md5Password = _Md5Password;
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

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
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

    public String getMd5Password() {
        return Md5Password;
    }

    public void setMd5Password(String md5Password) {
        Md5Password = md5Password;
    }

    public int compareTo(Customer o) {
        if( this.getLastName().equalsIgnoreCase( o.getLastName() ) )
        {
            return this.getFirstName().compareTo( o.getFirstName() );
        }
        else
        {
            return this.getLastName().compareTo( o.getLastName() );
        }
    }
}

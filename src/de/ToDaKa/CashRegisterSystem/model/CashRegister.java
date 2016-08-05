package de.ToDaKa.CashRegisterSystem.model;

import de.ToDaKa.CashRegisterSystem.storage.core.AbstractDatabaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CashRegister extends AbstractDatabaseEntity implements Serializable {
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "m_CashRegister")
    private List<Bon> m_Bon=new ArrayList<Bon>();

    public CashRegister() {
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


}

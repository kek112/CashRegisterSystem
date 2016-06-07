package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Bon {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_BonID;
    @Temporal(TemporalType.DATE)
    private Date m_Date;
    @Temporal(TemporalType.TIME)
    private Date m_Time;
    private int m_CashierID;
    private int m_CashRegisterID;
    private int m_CustomerID;

}

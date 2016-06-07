package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;

@Entity
public class CashRegister {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_CashregisterID;
    private int m_LocationID;
}

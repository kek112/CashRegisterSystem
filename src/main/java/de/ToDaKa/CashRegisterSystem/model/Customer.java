package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_CustomerID;
    private String m_FirstName;
    private String m_LastName;
}

package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;

@Entity
public class Cashier {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_Cashier;
    private String m_FirstName;
    private String m_LastName;
    private int m_PositionID;
}

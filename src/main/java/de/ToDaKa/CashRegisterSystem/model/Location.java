package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_LocationID;
    private String m_Name;
}

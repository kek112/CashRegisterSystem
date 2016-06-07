package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;

@Entity
public class Position {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_PositionID;
    private String m_Name;

}

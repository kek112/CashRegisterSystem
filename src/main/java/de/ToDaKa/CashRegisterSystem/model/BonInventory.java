package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BonInventory {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_BonInventoryID;
    private long m_Barcode;
    private int m_Amount;
    @Temporal(TemporalType.DATE)
    private Date m_Date;
    @Temporal(TemporalType.TIME)
    private Date m_Time;
    private int m_BonID;
    private float m_Price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="InventoryID")
    private Inventory m_Inventory;
}

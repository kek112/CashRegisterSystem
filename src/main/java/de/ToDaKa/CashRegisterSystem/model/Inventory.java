package de.ToDaKa.CashRegisterSystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inventory {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int m_ID;
    private long m_Barcode;
    private String m_Name;
    private int m_Amount;
    private float m_Price;
    private boolean m_IsFood;
    @OneToMany(mappedBy = "m_Inventory")
    private List<BonInventory> m_BonInventory;
    public Inventory()
    {

    }
    public Inventory(long Barcode, String Name, int Amount, float Price, boolean IsFood)
    {
        this.m_Barcode=Barcode;
        this.m_Name=Name;
        this.m_Amount=Amount;
        this.m_Price=Price;
        this.m_IsFood=IsFood;
    }
    public String getName()
    {
        return this.m_Name;
    }
    public int getID()
    {
        return this.m_ID;
    }

}

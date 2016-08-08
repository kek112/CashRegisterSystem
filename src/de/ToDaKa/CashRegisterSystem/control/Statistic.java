package de.ToDaKa.CashRegisterSystem.control;

import de.ToDaKa.CashRegisterSystem.model.Bon;
import de.ToDaKa.CashRegisterSystem.model.BonInventory;
import de.ToDaKa.CashRegisterSystem.model.CashRegisterSystem;

import java.util.Date;
import java.util.List;

/**
 Class: EmployeeAdvancedTableViewScreen
 @author Tobias Rieß


 **/
public class Statistic
{
    public static float statistic (CashRegisterSystem CRS, Date ToDate)
    {
        float sales = 0;

        List<Bon> _Bon = CRS.getBon();

        for (int BonIterrator = 0; BonIterrator <_Bon.size(); BonIterrator++)
        {
            if(_Bon.get(BonIterrator).getCreated().after(ToDate))
            {
                List<BonInventory> _BonInventory = _Bon.get(BonIterrator).getBonInventory();
                for(int BonInventoryIt = 0; BonInventoryIt < _BonInventory.size(); BonInventoryIt++)
                {
                    sales += _BonInventory.get(BonInventoryIt).getAmount()*_BonInventory.get(BonInventoryIt).getPrice();
                }
            }
        }
        return sales;
    }
}

package de.ToDaKa.CashRegisterSystem.control;

import de.ToDaKa.CashRegisterSystem.model.Bon;
import de.ToDaKa.CashRegisterSystem.model.BonInventory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Tobias on 21.06.2016.
 */
public class BonCreater
{
    public void createBonPDF(Bon _Bon, String _Filename)
    {

    }

    public boolean createBonTxt(Bon _Bon, String _Filename)
    {
        try
        {
            String name = _Bon.getId().toString();
            FileWriter writer = new FileWriter(name + ".txt");

            writer.write("Bon " + name + "\n\n");

            float sum = 0;

            for(int i = 0; i < _Bon.getBonInventory().size(); i++)
            {
                BonInventory currentArticle = _Bon.getBonInventory().get(i);

                writer.write(currentArticle.getAmount() + "x" + currentArticle.getInventory().getName() + "\t" + currentArticle.getPrice() + "\n");
                sum += currentArticle.getPrice();
            }

            writer.write("Summe:" + "\t\t" + sum);

            writer.close();
            return true;
        }
        catch(IOException e)
        {
            System.out.println("Error" + e.getMessage());
            System.out.println("Error writing File");
            return false;
        }


    }
    public void sendBon(String _Filename)
    {

    }
}

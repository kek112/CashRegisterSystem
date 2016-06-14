package de.ToDaKa.CashRegisterSystem;

import de.ToDaKa.CashRegisterSystem.model.*;
import javafx.geometry.Pos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;


public class MainClass
{
    public static void main(String[] args)
    {
        // test main

        //Create Cashier and Position
        Position Admin=new Position("Hauptkassierer");
        Position User=new Position("Kassierer");

        Cashier CurrentCashier=new Cashier("Daniel", "Albrecht", Admin);

        //Create Cash Register and Location
        Location CurrentLocation =new Location("REWE Eislebener Straße");
        CashRegister CurrentCashRegister =new CashRegister(CurrentLocation);


        //Create Bon
        Bon CurrentBon=new Bon(new Date(),CurrentCashier, CurrentCashRegister);


        //Create a Article in the Inventory
        Inventory CurrentArticle=new Inventory(123456789,"Laptop", 5,299.99f, false);

        //Add Article
        new BonInventory(2,new Date(),CurrentArticle.getPrice(),CurrentBon, CurrentArticle);

        CurrentArticle=new Inventory(134123141,"Tastatur", 2,19.99f, false);
        new BonInventory(2,new Date(),CurrentArticle.getPrice(),CurrentBon, CurrentArticle);

        System.out.println("Datum: "+CurrentBon.getDateTime());
        System.out.println(CurrentBon.getCashier().getPosition().getName()+": "+CurrentBon.getCashier().getLastName()+", "+CurrentBon.getCashier().getFirstName()+"\n");

        System.out.println("Artikel:");
        BonInventory CurrentBonInventory;
        for(int i=0;i<CurrentBon.getBonInventory().size();i++)
        {
            CurrentBonInventory=CurrentBon.getBonInventorys().get(i);

            System.out.print(CurrentBonInventory.getAmount()+" ");
            System.out.print(CurrentBonInventory.getPrice()+" ");
            System.out.print(CurrentBonInventory.getInventory().getName()+"\n");
        }


    }
}

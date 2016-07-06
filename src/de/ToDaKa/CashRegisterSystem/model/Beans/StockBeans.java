package de.ToDaKa.CashRegisterSystem.model.Beans;

import javafx.beans.property.*;

/**
 * Created by karls_000 on 23.06.2016.
 */
public class StockBeans {

    private StringProperty Barcode = new SimpleStringProperty(this, "Barcode", "0");
    public String getBarcode() {
        return Barcode.get();
    }
    public StringProperty BarcodeProperty() {
        return Barcode;
    }
    public void setBarcode(String Barcode) {
        this.Barcode.set(Barcode);
    }

    private StringProperty Name = new SimpleStringProperty(this, "Name", "");
    public String getName() {
        return Name.get();
    }
    public StringProperty NameProperty() {
        return Name;
    }
    public void setName(String Name) {
        this.Name.set(Name);
    }

    private IntegerProperty Amount = new SimpleIntegerProperty(this, "Anzahl", 0);
    public int getAmount() {
        return Amount.get();
    }
    public IntegerProperty AmountProperty() {
        return Amount;
    }
    public void setAmount(int Amount) {
        this.Amount.set(Amount);
    }

    private DoubleProperty Price = new SimpleDoubleProperty(this, "Preis", 0.0);
    public Double getPrice() {
        return Price.get();
    }
    public DoubleProperty PreisProperty() {
        return Price;
    }
    public void setPrice(double Price) {
        this.Price.set(Price);
    }

    private StringProperty isFood = new SimpleStringProperty(this, "Lebensmittel", "");
    public String getIsFood() {
        return isFood.get();
    }
    public StringProperty isFoodProperty() {
        return isFood;
    }
    public void setIsFood(String isFood) {
        this.isFood.set(isFood);
    }

    public String printString()
    {
        String temp;
        return "Barcode: " + getBarcode() + " | Name: " + getName() + " | Anzahl: " + getAmount() + " | Preis: " + getPrice() + " | Lebensmittel: " + getIsFood();
    }
}

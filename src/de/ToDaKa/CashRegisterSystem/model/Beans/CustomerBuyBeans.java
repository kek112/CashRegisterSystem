package de.ToDaKa.CashRegisterSystem.model.Beans;

import javafx.beans.property.*;
import javafx.beans.property.StringProperty;


public class CustomerBuyBeans {



    private LongProperty Barcode= new SimpleLongProperty(this,"Barocde",0);
    public long getBarcode() {
        return Barcode.get();
    }
    public LongProperty BarcodeProperty() {
        return Barcode;
    }
    public void setBarcode(long Barcode) {
        this.Barcode.set(Barcode);
    }

    private StringProperty ArticleName = new SimpleStringProperty(this, "ArticleName", "");
    public String getArticleName() {return ArticleName.get();}
    public StringProperty ArticleNameProperty() {return ArticleName;}
    public void setArticleName(String ArticleName) {
        this.ArticleName.set(ArticleName);
    }

    private IntegerProperty amount =new SimpleIntegerProperty(this, "amount", 0);
    public int getAmount() {return amount.get();}
    public IntegerProperty amountProperty() {return amount;}
    public void setAmount(int amount) {
        this.amount.set(amount);
    }


    private FloatProperty Price =new SimpleFloatProperty(this, "Price", 0);
    public float getPrice() {
        return Price.get();
    }
    public FloatProperty PriceProperty() {
        return Price;
    }
    public void setPrice(float Price) {
        this.Price.set(Price);
    }

    private StringProperty isFood =new SimpleStringProperty(this, "isFood", "");
    public String getIsFood() {
        return isFood.get();
    }
    public StringProperty isFoodProperty() {
        return isFood;
    }
    public void setIsFood(String isFood) {
        this.isFood.set(isFood);
    }

    //for console printing purposes
    public String toString() {

        return getBarcode()+" " + getArticleName() +" | Amount: "+ getAmount() + " | Price: " + getPrice() + " | Is Food: " + getIsFood();
    }
}

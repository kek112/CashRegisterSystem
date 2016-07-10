package de.ToDaKa.CashRegisterSystem.model.Beans;

import javafx.beans.property.*;


public class SellingBeans {



    private IntegerProperty BonID = new SimpleIntegerProperty(this, "BonID", 0);
    public int getBonID() {
        return BonID.get();
    }
    public IntegerProperty BonIDProperty() {
        return BonID;
    }
    public void setBonID(int BonID) {
        this.BonID.set(BonID);
    }

    private StringProperty Date = new SimpleStringProperty(this, "Date", "");
    public String getDate() {return Date.get();}
    public StringProperty DateProperty() {return Date;}
    public void setDate(String Date) {
        this.Date.set(Date);
    }

    private FloatProperty Price = new SimpleFloatProperty(this, "Price", 0);
    public Float getPrice() {return Price.get();}
    public FloatProperty PriceProperty() {return Price;}
    public void setPrice(float Price) {
        this.Price.set(Price);
    }

    private StringProperty Customer = new SimpleStringProperty(this, "Customer", "");
    public String getCustomer() {
        return Customer.get();
    }
    public StringProperty CustomerProperty() {
        return Customer;
    }
    public void setCustomer(String Customer) {
        this.Customer.set(Customer);
    }

}

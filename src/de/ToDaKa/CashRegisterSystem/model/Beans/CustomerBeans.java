package de.ToDaKa.CashRegisterSystem.model.Beans;

import javafx.beans.property.*;
/**
 Class: CustomerBeans
 @author Karl-Erik Kley

 Represents the storage for the usage in the FX application.
 **/

public class CustomerBeans {



    private LongProperty CustomerID = new SimpleLongProperty(this, "CustomerID", 0);
    public long getCustomerID() {
        return CustomerID.get();
    }
    public LongProperty CustomerIDProperty() {
        return CustomerID;
    }
    public void setCustomerID(long CustomerID) {
        this.CustomerID.set(CustomerID);
    }

    private StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    public String getLastName() {return lastName.get();}
    public StringProperty lastNameProperty() {return lastName;}
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    private StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    public String getFirstName() {return firstName.get();}
    public StringProperty firstNameProperty() {return firstName;}
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }


    private StringProperty Telephone = new SimpleStringProperty(this, "Telephone", "");
    public String getTelephone() {
        return Telephone.get();
    }
    public StringProperty TelephoneProperty() {
        return Telephone;
    }
    public void setTelephone(String Telephone) {
        this.Telephone.set(Telephone);
    }

    private StringProperty Birthday = new SimpleStringProperty(this, "Birthday", "");
    public String getBirthday() {
        return Birthday.get();
    }
    public StringProperty BirthdayProperty() {
        return Birthday;
    }
    public void setBirthday(String Birthday) {
        this.Birthday.set(Birthday);
    }

    private StringProperty gender = new SimpleStringProperty(this, "gender", "");
    public String getGender() {
        return gender.get();
    }
    public StringProperty genderProperty() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }


    //for console printing purposes
    public String toString() {

        return getGender()+" " + getFirstName() + " " + getLastName() + " | Birthday: " + getBirthday() + " | Telephone: " + getTelephone();
    }
}

package de.ToDaKa.CashRegisterSystem.model.Beans;

import javafx.beans.property.*;
import sun.util.calendar.BaseCalendar;

/**
 Class: EmployeeBeans
 @author Karl-Erik Kley

 Represents the storage for the usage in the FX application.
 **/
public class EmployeeBeans
{

        private LongProperty EmployeeNr = new SimpleLongProperty(this, "MitarbeiterNr", 0);
        public long getEmployeeNr() {
            return EmployeeNr.get();
        }
        public LongProperty EmployeeNrProperty() {
            return EmployeeNr;
        }
        public void setEmployeeNr(Long EmployeeNr) {
            this.EmployeeNr.set(EmployeeNr);
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

        private StringProperty FirstName = new SimpleStringProperty(this, "Vorname", "");
        public String getFirstName() {
        return FirstName.get();
    }
        public StringProperty FirstNameProperty() {
        return FirstName;
    }
        public void setFirstName(String FirstName) {
        this.FirstName.set(FirstName);
    }


        private StringProperty PhoneNr = new SimpleStringProperty(this, "Telefon", "0");
        public String getPhoneNr() {
            return PhoneNr.get();
        }
        public StringProperty PhoneNrProperty() {
            return PhoneNr;
        }
        public void setPhoneNr(String PhoneNr) {
            this.PhoneNr.set(PhoneNr);
        }

        private  StringProperty Birthday = new SimpleStringProperty(this, "Geburtstag", "0");
        public String getBirthday() {return Birthday.get();}
        public StringProperty BirthdayProperty() {
            return Birthday;
        }
        public void setBirthday (String Birthday) {
            this.Birthday.set(Birthday);
        }

        private StringProperty Rights = new SimpleStringProperty(this, "Berechtigung", "");
        public String getRights() {
            return Rights.get();
        }
        public StringProperty RightsProperty() {
            return Rights;
        }
        public void setRights(String Rights) {
            this.Rights.set(Rights);
        }

        public String printString()
        {
            String temp;
            return "MitarbeiterNr: " + getEmployeeNr() + " | Name: " + getName() + " | Vorname: " + getFirstName() + " | Telefon: " + getPhoneNr() + " | Geburtstag: " + getBirthday() + " | Berechtigung: " + getRights();
        }
}

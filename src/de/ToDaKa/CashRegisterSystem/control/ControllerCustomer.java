package de.ToDaKa.CashRegisterSystem.control;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import de.ToDaKa.CashRegisterSystem.model.Beans.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;



public class ControllerCustomer implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="CustomerIDCCol"
    private TableColumn<CustomerBeans, String> CustomerIDCol;


    @FXML // fx:id="firstNameCol"
    private TableColumn<CustomerBeans, String> firstNameCol;

    @FXML // fx:id="lastNameCol"
    private TableColumn<CustomerBeans, String> lastNameCol;

    @FXML // fx:id="TelephoneCol"
    private TableColumn<CustomerBeans, String> TelephoneCol;

    @FXML // fx:id="BirthdayCol"
    private TableColumn<CustomerBeans, String> BirthdayCol;

    @FXML // fx:id="genderCol"
    private TableColumn<CustomerBeans, String> genderCol;

    @FXML // fx:id="firstNameField"
    private TextField firstNameField;

    @FXML // fx:id="lastNameField"
    private TextField lastNameField;

    @FXML // fx:id="BirthdayField"
    private TextField BirthdayField;

    @FXML // fx:id="TelephoneField"
    private TextField TelephoneField;

    @FXML
    private TextField filterInput;

    @FXML // fx:id="genderBox"
    private ComboBox<String> genderBox;
    ObservableList<String> genderBoxData = FXCollections.observableArrayList();

    @FXML
    private TableView<CustomerBeans> CustomerTable;

    @FXML // fx:id="addBtn"
    private Button addBtn;

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn;

    @FXML
    private MenuBar fileMenu;

    ObservableList<CustomerBeans> observableCustomerBeansList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add Listener to filterField
        filterInput.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterCustomerList((String) oldValue, (String) newValue);

            }
        });
        //initialize editable attributes
        CustomerTable.setEditable(true);
        CustomerIDCol.setOnEditCommit(e -> CustomerIDCol_OnEditCommit(e));
        firstNameCol.setOnEditCommit(e -> firstNameCol_OnEditCommit(e));
        lastNameCol.setOnEditCommit(e -> lastNameCol_OnEditCommit(e));
        TelephoneCol.setOnEditCommit(e -> TelephoneCol_OnEditCommit(e));
        BirthdayCol.setOnEditCommit(e -> BirthdayCol_OnEditCommit(e));
        genderCol.setOnEditCommit(e -> genderCol_OnEditCommit(e));

        CustomerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        CustomerIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        TelephoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        BirthdayCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setCellFactory(TextFieldTableCell.forTableColumn());

        CustomerIDCol.setCellValueFactory(cellData -> cellData.getValue().CustomerIDProperty());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TelephoneCol.setCellValueFactory(cellData -> cellData.getValue().TelephoneProperty());
        BirthdayCol.setCellValueFactory(cellData -> cellData.getValue().BirthdayProperty());
        genderCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());


        //initialize gender ComboBox
        genderBoxData.add(new String("Herr"));
        genderBoxData.add(new String("Frau"));

        genderBox.setItems(genderBoxData);


        addBtn.setDisable(true);
        deleteBtn.setDisable(true);
        CustomerTable.setItems(observableCustomerBeansList);
        CustomerTable.setEditable(true);
        CustomerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        CustomerTable.setPlaceholder(new Label("Tabelle ist leer"));

        firstNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (firstNameField.isFocused()) {
                    addBtn.setDisable(false);
                }
            }
        });
        CustomerTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (CustomerTable.isFocused()) {
                    deleteBtn.setDisable(false);
                }
            }
        });
    }//end initialize

    /*
    ----------------------------------------------Control handlers---------------------------------------------
     */
    public void handleAddButtonClick(ActionEvent event) {
        /*
        Get input from user and add to Table
         */
            if (isValidInput(event)) {
                if (genderBox.getValue().equals("Herr")) {
                        CustomerBeans customerBeans = new CustomerBeans();
                        customerBeans.setFirstName(firstNameField.getText());
                        customerBeans.setLastName(lastNameField.getText());
                        customerBeans.setTelephone(TelephoneField.getText());
                        customerBeans.setBirthday(BirthdayField.getText());
                        customerBeans.setGender(genderBox.getValue());
                        observableCustomerBeansList.add(customerBeans);
                        System.out.println(customerBeans.toString());
                        firstNameField.clear();
                        lastNameField.clear();
                        BirthdayField.clear();
                        TelephoneField.clear();
                        genderBox.setValue("Anrede");
                }
                if (genderBox.getValue().equals("Frau")) {
                    CustomerBeans customerBeans = new CustomerBeans();
                    customerBeans.setFirstName(firstNameField.getText());
                    customerBeans.setLastName(lastNameField.getText());
                    customerBeans.setTelephone(TelephoneField.getText());
                    customerBeans.setBirthday(BirthdayField.getText());
                    customerBeans.setGender(genderBox.getValue());
                    observableCustomerBeansList.add(customerBeans);
                    System.out.println(customerBeans.toString());
                    firstNameField.clear();
                    lastNameField.clear();
                    BirthdayField.clear();
                    TelephoneField.clear();
                    genderBox.setValue("Anrede");
                }

        }
    }
    /*
    In case of empty fields. Gives alert for respective empty field and requests focus on that field.
     */
    private boolean isValidInput(ActionEvent event) {

        Boolean validInput = true;

        if(firstNameField == null || firstNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Vorname ist leer");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                firstNameField.requestFocus();
            }
        }
        if(lastNameField == null || lastNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyLastName = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("Nachname ist leer");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if (emptyLastName.getResult() == ButtonType.OK) {
                emptyLastName.close();
                lastNameField.requestFocus();
            }
        }
        if(TelephoneField == null || TelephoneField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyTelephone = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyTelephone.setContentText("Telefonnummer ist leer");
            emptyTelephone.initModality(Modality.APPLICATION_MODAL);
            emptyTelephone.initOwner(owner);
            emptyTelephone.showAndWait();
            if (emptyTelephone.getResult() == ButtonType.OK) {
                emptyTelephone.close();
                TelephoneField.requestFocus();
            }
        }
        if(BirthdayField == null || BirthdayField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyBirthday = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyBirthday.setContentText("Geburtsdatum ist leer");
            emptyBirthday.initModality(Modality.APPLICATION_MODAL);
            emptyBirthday.initOwner(owner);
            emptyBirthday.showAndWait();
            if (emptyBirthday.getResult() == ButtonType.OK) {
                emptyBirthday.close();
                BirthdayField.requestFocus();
            }
        }

        if(genderBox == null || genderBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Gender is EMPTY");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK) {
                emptyGender.close();
                genderBox.requestFocus();
            }
        }
        return validInput;
    }
    /*
    handle column edits
     */
    public void CustomerIDCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setCustomerID(cellEditEvent.getNewValue());
    }
    public void firstNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setFirstName(cellEditEvent.getNewValue());
    }
    public void lastNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setLastName(cellEditEvent.getNewValue());
    }
    public void TelephoneCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setTelephone(cellEditEvent.getNewValue());
    }
    public void BirthdayCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setBirthday(cellEditEvent.getNewValue());
    }
    public void genderCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBeans, String>) e;
        CustomerBeans customerBeans = cellEditEvent.getRowValue();
        customerBeans.setGender(cellEditEvent.getNewValue());
    }
    public void handleDeleteButtonClick(ActionEvent event) {
        if(!observableCustomerBeansList.isEmpty()) {
            System.out.println("Löschen Button gedrückt");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "OK", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Sind Sie sicher das diese Aktion vortgesetzt werden soll?\n\nAKTION KANN NICHT RÜCKGÄNGIG GEMACHT WERDEN");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                observableCustomerBeansList.removeAll(CustomerTable.getSelectionModel().getSelectedItems());
                CustomerTable.getSelectionModel().clearSelection();
            }
            else {
                deleteAlert.close();
            }
        }
    }
    public void handleClearButtonClick(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        BirthdayField.clear();
        TelephoneField.clear();
        genderBox.setValue("Anrede");
    }
    //filter table by first or last name
    public void filterCustomerList(String oldValue, String newValue) {
        ObservableList<CustomerBeans> filteredList = FXCollections.observableArrayList();
        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            CustomerTable.setItems(observableCustomerBeansList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(CustomerBeans customerBeans : CustomerTable.getItems()) {
                String filterFirstName = customerBeans.getFirstName();
                String filterLastName = customerBeans.getLastName();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(customerBeans);
                }
            }
            CustomerTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event) {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kunden Tabelle speichern");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableCustomerBeansList.isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "LEERE TABELLE", ButtonType.OK);
            emptyTableAlert.setContentText("Es gibt nichts zu speichern");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null) {
                saveFile(CustomerTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<CustomerBeans> observableCustomerBeansList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(CustomerBeans customerBeans : observableCustomerBeansList) {
                outWriter.write(customerBeans.toString());
                outWriter.newLine();
            }
            System.out.println(observableCustomerBeansList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
                ioAlert.setContentText("unerwarteter Fehler");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
    public void closeApp(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "OK", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Sind Sie sicher das Sie beenden wollen?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }
}







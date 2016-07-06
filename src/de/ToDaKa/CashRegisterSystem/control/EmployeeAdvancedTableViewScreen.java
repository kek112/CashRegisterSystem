package de.ToDaKa.CashRegisterSystem.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

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
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

import de.ToDaKa.CashRegisterSystem.model.Beans.*;

/**
 * Created by karls_000 on 27.06.2016.
 */

public class EmployeeAdvancedTableViewScreen implements Initializable {

    ControllerFunctions CFobject=new ControllerFunctions();


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TABLEVIEW
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<EmployeeBeans> employeeTable;

    ObservableList<EmployeeBeans> observableEmployeeBeansList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<EmployeeBeans, String> NameCol;
    @FXML
    private TableColumn<EmployeeBeans, Number> EmployeeCol;
    @FXML
    private TableColumn<EmployeeBeans, String> FirstNameCol;
    @FXML
    private TableColumn<EmployeeBeans, String> PhoneNrCol;
    @FXML
    private TableColumn<EmployeeBeans, String> BirthdayCol;
    @FXML
    private TableColumn<EmployeeBeans, String> RightsCol;

    @FXML
    private TextField NameField;
    @FXML
    private TextField BirthdayField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField TelephoneField;
    @FXML
    private TextField filterInput;
    @FXML
    private ComboBox RightsBox;
    ObservableList<String> RightsBoxData = FXCollections.observableArrayList();

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //INIT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void initialize(URL url,ResourceBundle rb)
    {
        filterInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterNameList((String) oldValue,(String) newValue);
            }
        });
        employeeTable.setEditable(true);



        NameCol.setOnEditCommit         (e->NameCol_OnEditCommit(e));
        FirstNameCol.setOnEditCommit    (e->FirstName_OnEditCommit(e));
        PhoneNrCol.setOnEditCommit      (e->Telephone_OnEditCommit(e));
        BirthdayCol.setOnEditCommit     (e->Birthday_OnEditCommit(e));
        RightsCol.setOnEditCommit       (e->Rights_OnEditCommit(e));
        EmployeeCol.setOnEditCommit     (e->EmployeeNrCol_OnEditCommit(e));

        employeeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        EmployeeCol.setCellFactory              (TextFieldTableCell.forTableColumn(new NumberStringConverter()));

        FirstNameCol.setCellFactory             (TextFieldTableCell.forTableColumn());
        NameCol.setCellFactory                  (TextFieldTableCell.forTableColumn());
        BirthdayCol.setCellFactory              (TextFieldTableCell.forTableColumn());
        PhoneNrCol.setCellFactory               (TextFieldTableCell.forTableColumn());
        RightsCol.setCellFactory                (TextFieldTableCell.forTableColumn());

        EmployeeCol.setCellValueFactory      (cellData->cellData.getValue().EmployeeNrProperty());
        FirstNameCol.setCellValueFactory    (cellData->cellData.getValue().FirstNameProperty());
        NameCol.setCellValueFactory         (cellData->cellData.getValue().NameProperty());
        PhoneNrCol.setCellValueFactory      (cellData->cellData.getValue().PhoneNrProperty());
        BirthdayCol.setCellValueFactory     (cellData->cellData.getValue().BirthdayProperty());
        RightsCol.setCellValueFactory       (cellData->cellData.getValue().RightsProperty());

        RightsBoxData.add("Kassierer");
        RightsBoxData.add("Teamleiter");
        RightsBox.setItems(RightsBoxData);

        //////////////////////////////////////////////
        /////////////////////////////////////////////
        //FILL LIST Funktion    FillBeanList()
        //////////////////////////////////////////////
        /////////////////////////////////////////////////
        addBtn.setDisable          (true);
        deleteBtn.setDisable       (true);
        employeeTable.setItems     (observableEmployeeBeansList);
        employeeTable.setEditable  (true);

        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        employeeTable.setPlaceholder(new Label("Keine Einträge vorhanden"));

        NameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(NameField.isFocused())
                {
                    addBtn.setDisable(false);
                }
            }
        });
        FirstNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(FirstNameField.isFocused())
                {
                    addBtn.setDisable(false);
                }
            }
        });
        employeeTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(employeeTable.isFocused())
                {
                    deleteBtn.setDisable(false);
                }
            }
        });

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HANDLES on Edit
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void EmployeeNrCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,Number>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setEmployeeNr(cellEditEvent.getNewValue().intValue());

    }

    @FXML
    void NameCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,String>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setName(cellEditEvent.getNewValue());

        EmployeeBeans employeerow= (EmployeeBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<EmployeeBeans, String>) e).getTablePosition().getRow());

        System.out.println(employeerow.getEmployeeNr());
    }
    @FXML
    void FirstName_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,String>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setFirstName(cellEditEvent.getNewValue());

        EmployeeBeans employeerow= (EmployeeBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<EmployeeBeans, String>) e).getTablePosition().getRow());

        System.out.println(employeerow.getEmployeeNr());
    }
    @FXML
    void Telephone_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,String>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setPhoneNr(cellEditEvent.getNewValue());

        EmployeeBeans employeerow= (EmployeeBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<EmployeeBeans, String>) e).getTablePosition().getRow());

        System.out.println(employeerow.getEmployeeNr());
    }

    @FXML
    void Birthday_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,String>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setBirthday(cellEditEvent.getNewValue());

        EmployeeBeans employeerow= (EmployeeBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<EmployeeBeans, String>) e).getTablePosition().getRow());

        System.out.println(employeerow.getEmployeeNr());
    }
    @FXML
    void Rights_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<EmployeeBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<EmployeeBeans,String>) e;
        EmployeeBeans employeeBeans = cellEditEvent.getRowValue();
        employeeBeans.setRights(cellEditEvent.getNewValue());

        EmployeeBeans employeerow= (EmployeeBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<EmployeeBeans, String>) e).getTablePosition().getRow());

        System.out.println(employeerow.getEmployeeNr());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HANDLE Buttons
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void handleAddButtonClick(ActionEvent event)
    {
        if(isValidInput(event))
        {
            if(RightsBox.getValue().equals("Kassierer"))
            {

                EmployeeBeans employeeBeans = new EmployeeBeans();
                employeeBeans.setEmployeeNr  (   getnextNumber());
                employeeBeans.setName        (   NameField.getText());
                employeeBeans.setFirstName   (   FirstNameField.getText());
                employeeBeans.setPhoneNr     (   TelephoneField.getText());
                employeeBeans.setBirthday    (   BirthdayField.getText());
                employeeBeans.setRights      (   RightsBox.getValue().toString());

                observableEmployeeBeansList.add(employeeBeans);
                System.out.println(employeeBeans.printString());


                NameField.clear();
                FirstNameField.clear();
                TelephoneField.clear();
                BirthdayField.clear();
                RightsBox.setValue("Berechtigung");


            }
            if(RightsBox.getValue().equals("Teamleiter"))
            {
                EmployeeBeans employeeBeans = new EmployeeBeans();
                employeeBeans.setEmployeeNr  (   5);
                employeeBeans.setName        (   NameField.getText());
                employeeBeans.setFirstName   (   FirstNameField.getText());
                employeeBeans.setPhoneNr     (   TelephoneField.getText());
                employeeBeans.setBirthday    (   BirthdayField.getText());
                employeeBeans.setRights      (   RightsBox.getValue().toString());

                observableEmployeeBeansList.add(employeeBeans);
                System.out.println(employeeBeans.printString());


                NameField.clear();
                FirstNameField.clear();
                TelephoneField.clear();
                BirthdayField.clear();
                RightsBox.setValue("Berechtigung");
            }
        }
    }
    @FXML
    void handleDeleteButtonClick(ActionEvent event)
    {
        if(!observableEmployeeBeansList.isEmpty())
        {
            System.out.println("Löschen gedrückt");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Bestätigen", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Eintrag wirklich löschen?");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();

            if(deleteAlert.getResult() == ButtonType.OK)
            {
                observableEmployeeBeansList.removeAll(employeeTable.getSelectionModel().getSelectedItems());
                employeeTable.getSelectionModel().clearSelection();
            }
            else
            {
                deleteAlert.close();
            }
        }
    }
    @FXML
    void handleSave()
    {}
    @FXML
    void handleBackButton(ActionEvent e)throws Exception
    {
        CFobject.switchScene(e,"");

    }

    @FXML
    void handleClearButtonClick()
    {
        NameField.clear();
        FirstNameField.clear();
        TelephoneField.clear();
        BirthdayField.clear();
        RightsBox.setValue("Berechtigung");
    }
    @FXML
    void handleSave(ObservableList<EmployeeBeans> observableStockList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(EmployeeBeans employeeBeans : observableStockList) {
                outWriter.write(employeeBeans.printString());
                outWriter.newLine();
            }
            System.out.println(observableStockList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "Ups!", ButtonType.OK);
            ioAlert.setContentText("Ein Fehler ist aufgetreten.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }




    void filterNameList(String oldValue,String newValue)
    {
        ObservableList<EmployeeBeans> filteredList = FXCollections.observableArrayList();

        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null)
        {
            employeeTable.setItems(observableEmployeeBeansList);
        }
        else
        {
            newValue = newValue.toUpperCase();
            for(EmployeeBeans items : employeeTable.getItems())
            {
                String filterName = items.getName();

                if(filterName.toUpperCase().contains(newValue) )
                {
                    filteredList.add(items);
                }
            }

            employeeTable.setItems(filteredList);
        }

    }

    boolean isValidInput(ActionEvent event)
    {
        Boolean validInput = true;

        //Check BArcode for empty
        if(NameField == null || NameField.getText().trim().isEmpty())
        {
            validInput = false;
            Alert emptyBarcode = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyBarcode.setContentText("Barcode ist leer");
            emptyBarcode.initModality(Modality.APPLICATION_MODAL);
            emptyBarcode.initOwner(owner);
            emptyBarcode.showAndWait();
            if(emptyBarcode.getResult() == ButtonType.OK)
            {
                emptyBarcode.close();
                NameField.requestFocus();
            }
        }


        //Check Name  for empty
        if(FirstNameField == null || FirstNameField.getText().trim().isEmpty())
        {
            validInput = false;
            Alert emptyName = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyName.setContentText("Name ist leer");
            emptyName.initModality(Modality.APPLICATION_MODAL);
            emptyName.initOwner(owner);
            emptyName.showAndWait();
            if(emptyName.getResult() == ButtonType.OK) {
                emptyName.close();
                FirstNameField.requestFocus();
            }
        }


        //Check  Amount for empty
        if( BirthdayField== null || BirthdayField.getText().trim().isEmpty())
        {
            validInput = false;
            Alert emptyAmount = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyAmount.setContentText("Anzahl ist leer");
            emptyAmount.initModality(Modality.APPLICATION_MODAL);
            emptyAmount.initOwner(owner);
            emptyAmount.showAndWait();
            if(emptyAmount.getResult() == ButtonType.OK) {
                emptyAmount.close();
                BirthdayField.requestFocus();
            }
        }
        //Check  Amount for empty
        if(TelephoneField == null || TelephoneField.getText().trim().isEmpty())
        {
            validInput = false;
            Alert emptyPrice = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyPrice.setContentText("Preis ist leer");
            emptyPrice.initModality(Modality.APPLICATION_MODAL);
            emptyPrice.initOwner(owner);
            emptyPrice.showAndWait();
            if(emptyPrice.getResult() == ButtonType.OK) {
                emptyPrice.close();
                TelephoneField.requestFocus();
            }
        }

        //Check  isFood for empty
        if(RightsBox == null || RightsBox.getValue().equals(""))
        {
            validInput = false;
            Alert emptyisFood = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyisFood.setContentText("Lebensmittel ist nicht gewählt");
            emptyisFood.initModality(Modality.APPLICATION_MODAL);
            emptyisFood.initOwner(owner);
            emptyisFood.showAndWait();

            if(emptyisFood.getResult() == ButtonType.OK)
            {
                emptyisFood.close();
                RightsBox.requestFocus();
            }
        }


        return validInput;

    }
    int getnextNumber()
    {
        return 2;
    }

    void FillBeanList()
    {
        EmployeeBeans TempEmp=new EmployeeBeans();

        /*getEntryfromDatase;
        TempEmp.setFirstName();
        TempEmp.setName();
        TempEmp.setEmployeeNr();
        TempEmp.setRights();
        TempEmp.setPhoneNr();
        TempEmp.setBirthday();
      */

        observableEmployeeBeansList.add(TempEmp);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

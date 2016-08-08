package de.ToDaKa.CashRegisterSystem.control;


import de.ToDaKa.CashRegisterSystem.CurrentUser;
import de.ToDaKa.CashRegisterSystem.Main;
import de.ToDaKa.CashRegisterSystem.model.Beans.CustomerBuyBeans;
import de.ToDaKa.CashRegisterSystem.model.Bon;
import de.ToDaKa.CashRegisterSystem.model.BonInventory;
import de.ToDaKa.CashRegisterSystem.model.Customer;
import de.ToDaKa.CashRegisterSystem.model.Inventory;
import de.ToDaKa.CashRegisterSystem.model.execptions.BonExistsException;
import de.ToDaKa.CashRegisterSystem.storage.IStorageController;
import de.ToDaKa.CashRegisterSystem.storage.JpaStorageController;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 Class: ControllerCustomerBuyScreen
 @author Karl-Erik Kley

 Provides the connection for the FXML window,
 to create the interface to the system
 **/

public class ControllerCustomerBuyScreen implements Initializable {

    Bon currentBon=null;
    ControllerFunctions CFObject=new ControllerFunctions();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="CustomerIDCCol"
    private TableColumn<CustomerBuyBeans, String> BarcodeCol;


    @FXML // fx:id="NameCol"
    private TableColumn<CustomerBuyBeans, String> NameCol;

    @FXML // fx:id="AmountCol"
    private TableColumn<CustomerBuyBeans, String> AmountCol;

    @FXML // fx:id="PriceCol"
    private TableColumn<CustomerBuyBeans, String> PriceCol;

    @FXML // fx:id="isFoodCol"
    private TableColumn<CustomerBuyBeans, String> isFoodCol;

    @FXML // fx:id="barcodeField"
    private TextField barcodeField;

    @FXML // fx:id="amountField"
    private TextField amountField;

    @FXML
    private TextField filterInput;

    @FXML
    private TableView<CustomerBuyBeans> StockTable;

    @FXML // fx:id="addButton"
    private Button addButton;

    @FXML // fx:id="ClearButton"
    private Button ClearButton;

    @FXML // fx:id="deleteButton"
    private Button deleteButton;

    @FXML // fx:id="CheckoutButton"
    private Button CheckoutButton;

    @FXML // fx:id="BackButton"
    private Button BackButton;


    @FXML
    private MenuBar fileMenu;

    ObservableList<CustomerBuyBeans> observableCustomerBuyBeansList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initialize editable attributes
        StockTable.setEditable(true);
        BarcodeCol.setOnEditCommit(e -> BarcodeCol_OnEditCommit(e));
        NameCol.setOnEditCommit(e -> NameCol_OnEditCommit(e));
        AmountCol.setOnEditCommit(e -> AmountCol_OnEditCommit(e));
        PriceCol.setOnEditCommit(e -> PriceCol_OnEditCommit(e));
        isFoodCol.setOnEditCommit(e -> isFoodCol_OnEditCommit(e));

        StockTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BarcodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        NameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        AmountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        PriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        isFoodCol.setCellFactory(TextFieldTableCell.forTableColumn());

        BarcodeCol.setCellValueFactory(cellData -> cellData.getValue().BarcodeProperty().asString());
        NameCol.setCellValueFactory(cellData -> cellData.getValue().ArticleNameProperty());
        AmountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asString());
        PriceCol.setCellValueFactory(cellData -> cellData.getValue().PriceProperty().asString());
        isFoodCol.setCellValueFactory(cellData -> cellData.getValue().isFoodProperty());

        addButton.setDisable(true);
        deleteButton.setDisable(true);
        StockTable.setItems(observableCustomerBuyBeansList);
        StockTable.setEditable(true);
        StockTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        StockTable.setPlaceholder(new Label("Tabelle ist leer"));

        barcodeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (barcodeField.isFocused()) {
                    addButton.setDisable(false);
                }
            }
        });
        StockTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (StockTable.isFocused()) {
                    deleteButton.setDisable(false);
                }
            }
        });
    }//end initialize
    private void update()
    {
        if(currentBon!=null)
        {
            for(int i=0;i<currentBon.getBonInventorys().size();i++)
            {
                CustomerBuyBeans CustomerBuyBeans = new CustomerBuyBeans();
                CustomerBuyBeans.setBarcode(currentBon.getBonInventorys().get(i).getInventory().getBarcode());
                CustomerBuyBeans.setAmount(currentBon.getBonInventory().get(i).getAmount());
                CustomerBuyBeans.setPrice(currentBon.getBonInventory().get(i).getPrice());
                CustomerBuyBeans.setArticleName(currentBon.getBonInventory().get(i).getInventory().getName());
                if(currentBon.getBonInventory().get(i).getInventory().IsFood())
                {
                    CustomerBuyBeans.setIsFood("Ja");

                }
                else
                {
                    CustomerBuyBeans.setIsFood("Nein");
                }
                observableCustomerBuyBeansList.add(CustomerBuyBeans);

            }
        }
    }
    /*
    ----------------------------------------------Control handlers---------------------------------------------
     */
    public void handleAddButtonClick(ActionEvent event) throws BonExistsException {
        /*
        Get input from user and add to Table
         */
            if (isValidInput(event))
            {
                Inventory currentInventory=Main.CRS.findInventory(Long.parseLong(barcodeField.getText()));
                if(currentInventory!=null)
                {
                    int Amount=Integer.parseInt(amountField.getText());
                                        if(currentInventory.getAmount()>=Amount)
                    {

                        IStorageController sc = new JpaStorageController();
                     try
                     {
                        Main.CRS=sc.loadCashRegisterSystem();
                    }
                        catch (StorageException e)
                    {
                        e.printStackTrace();
                    }
                        BonInventory currentBonInventory=null;
                        if(currentBon==null)
                        {
                            currentBon=new Bon();
                            Main.CRS.addBon(currentBon);
                            currentBon.setCashier(Main.CRS.findCashier(CurrentUser.getCurrentUserID()));
                            //TODO
                            currentBon.setCustomer(null);
                        }
                        else
                        {
                            for(int i=0;currentBon.getBonInventory().size()>i;i++)
                            {
                                if(currentBon.getBonInventory().get(i).getInventory().getBarcode()==Long.parseLong(barcodeField.getText()))
                                {
                                    currentBonInventory=currentBon.getBonInventorys().get(i);
                                    i=currentBon.getBonInventory().size();
                                }
                            }
                        }
                        if(currentBonInventory==null)
                        {
                            currentBonInventory=new BonInventory(Amount,currentBon, currentInventory);
                            currentBon.addBonInventory(currentBonInventory);
                            currentInventory.addBonInventory(currentBonInventory);


                        }
                        else
                        {
                            currentBonInventory.setAmount(Amount+currentBonInventory.getAmount());
                        }
                        int amountInventory=currentInventory.getAmount();
                        currentInventory.setAmount(amountInventory-Amount);


                        currentBon.setCashier(Main.CRS.findCashier(CurrentUser.getCurrentUserID()));
                        currentBon.getCashier().addBon(currentBon);

                        currentBon.setCustomer(null);

                        try
                        {
                            sc.saveCashRegisterSystem(Main.CRS);
                        }
                        catch (StorageException e)
                        {
                            e.printStackTrace();
                        }


                        observableCustomerBuyBeansList.removeAll(observableCustomerBuyBeansList);
                        barcodeField.clear();
                        amountField.setText("1");
                        update();
                        }
                        else
                    {
                        System.out.println("Nicht genug Artikel im Inventar");
                    }
                    }
            }

        }

    /*
    In case of empty fields. Gives alert for respective empty field and requests focus on that field.
     */
    private boolean isValidInput(ActionEvent event) {

        Boolean validInput = true;
        if (!barcodeField.getText().matches("[0-9]+") || barcodeField.getText().trim().isEmpty()) {
            validInput = false;


            Alert wrongBarcode = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            wrongBarcode.setContentText("Falscher Barcode");
            wrongBarcode.initModality(Modality.APPLICATION_MODAL);
            wrongBarcode.initOwner(owner);
            wrongBarcode.showAndWait();
            if (wrongBarcode.getResult() == ButtonType.OK) {
                wrongBarcode.close();
                barcodeField.requestFocus();
            }
        }
        if (!amountField.getText().matches("[0-9]+") || amountField.getText().trim().isEmpty()) {
            validInput = false;

            Alert wrongAmount = new Alert(Alert.AlertType.WARNING, "Warnung", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            wrongAmount.setContentText("Falsche Anzahl");
            wrongAmount.initModality(Modality.APPLICATION_MODAL);
            wrongAmount.initOwner(owner);
            wrongAmount.showAndWait();
            if (wrongAmount.getResult() == ButtonType.OK) {
                wrongAmount.close();
                amountField.requestFocus();
            }

        }

        return validInput;
    }

    /*
    handle column edits
     */
    public void BarcodeCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBuyBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBuyBeans, String>) e;
        CustomerBuyBeans customerBuyBeans = cellEditEvent.getRowValue();
        customerBuyBeans.setBarcode(Long.parseLong(cellEditEvent.getNewValue()));
    }
    public void NameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBuyBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBuyBeans, String>) e;
        CustomerBuyBeans customerBuyBeans = cellEditEvent.getRowValue();
        customerBuyBeans.setArticleName(cellEditEvent.getNewValue());
    }
    public void AmountCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBuyBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBuyBeans, String>) e;
        CustomerBuyBeans customerBuyBeans = cellEditEvent.getRowValue();
        customerBuyBeans.setAmount(Integer.parseInt(cellEditEvent.getNewValue()));
    }
    public void PriceCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBuyBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBuyBeans, String>) e;
        CustomerBuyBeans CustomerBuyBeans = cellEditEvent.getRowValue();
        CustomerBuyBeans.setPrice(Long.parseLong(cellEditEvent.getNewValue()));
    }
    public void isFoodCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<CustomerBuyBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<CustomerBuyBeans, String>) e;
        CustomerBuyBeans CustomerBuyBeans = cellEditEvent.getRowValue();
        if(cellEditEvent.getNewValue()=="Ja")
        {
            CustomerBuyBeans.setIsFood(cellEditEvent.getNewValue());
        }
        if(cellEditEvent.getNewValue()=="Nein")
        {
            CustomerBuyBeans.setIsFood(cellEditEvent.getNewValue());
        }
    }
    public void handleDeleteButtonClick(ActionEvent event) {
        if(!observableCustomerBuyBeansList.isEmpty()) {
            System.out.println("Löschen Button gedrückt");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "OK", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Sind Sie sicher das diese Aktion vortgesetzt werden soll?\n\nAKTION KANN NICHT RÜCKGÄNGIG GEMACHT WERDEN");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                int amountOnBon=StockTable.getSelectionModel().getSelectedItem().getAmount();
                long barcode=StockTable.getSelectionModel().getSelectedItem().getBarcode();
                int amountOnInventar=Main.CRS.findInventory(barcode).getAmount();

                Main.CRS.findInventory(barcode).setAmount(amountOnInventar+amountOnBon);



                observableCustomerBuyBeansList.removeAll(StockTable.getSelectionModel().getSelectedItems());
                StockTable.getSelectionModel().clearSelection();

            }
            else {
                deleteAlert.close();
            }
        }
    }
    public void handleClearButtonClick(ActionEvent event) {
        barcodeField.clear();
        amountField.setText("1");
    }

    public void handleCheckoutClick(ActionEvent event) {
        currentBon=null;
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kunden Tabelle speichern");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableCustomerBuyBeansList.isEmpty()) {
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
                saveFile(StockTable.getItems(), file);


                observableCustomerBuyBeansList.removeAll(StockTable.getItems());
                StockTable.getSelectionModel().clearSelection();

            }
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent event)throws Exception {
        currentBon=null;
        if(CurrentUser.isAdmin())
        {
            CFObject.switchScene(event,"Admin_Menu.fxml");
        }
        else
        {
            CFObject.switchScene(event,"Employee_Menu.fxml");
        }
    }
    public void saveFile(ObservableList<CustomerBuyBeans> observableCustomerBuyBeansList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(CustomerBuyBeans CustomerBuyBeans : observableCustomerBuyBeansList) {
                outWriter.write(CustomerBuyBeans.toString());
                outWriter.newLine();
            }
            System.out.println(observableCustomerBuyBeansList.toString());
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








package de.ToDaKa.CashRegisterSystem.control;


import de.ToDaKa.CashRegisterSystem.CurrentUser;
import de.ToDaKa.CashRegisterSystem.Main;
import de.ToDaKa.CashRegisterSystem.model.Beans.SellingBeans;
import de.ToDaKa.CashRegisterSystem.model.Bon;
import de.ToDaKa.CashRegisterSystem.model.Cashier;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 Class: ControllerSellingsTableViewScreen
 @author Karl-Erik Kley

 Provides the connection for the FXML window,
 to create the interface to the system
 **/
public class ControllerSellingsTableViewScreen implements Initializable {


    ControllerFunctions CFObject=new ControllerFunctions();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="BonCol"
    private TableColumn<SellingBeans, String> BonCol;


    @FXML // fx:id="DateCol"
    private TableColumn<SellingBeans, String> DateCol;

    @FXML // fx:id="TimeCol"
    private TableColumn<SellingBeans, String> TimeCol;

    @FXML // fx:id="PriceCol"
    private TableColumn<SellingBeans, String> PriceCol;

    @FXML // fx:id="CustomerCol"
    private TableColumn<SellingBeans, String> CustomerCol;

    @FXML
    private TextField filterInput;

    @FXML
    private TableView<SellingBeans> BonTable;

    @FXML // fx:id="BackButton"
    private Button BackButton;


    @FXML
    private MenuBar fileMenu;

    ObservableList<SellingBeans> observableSellingBeansList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        update();
        //initialize editable attributes
        BonTable.setEditable(true);
        CustomerCol.setOnEditCommit(e -> CustomerCol_OnEditCommit(e));

        BonTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BonCol.setCellFactory(TextFieldTableCell.forTableColumn());
        DateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        PriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        CustomerCol.setCellFactory(TextFieldTableCell.forTableColumn());

        BonCol.setCellValueFactory(cellData -> cellData.getValue().BonIDProperty().asString());
        DateCol.setCellValueFactory(cellData -> cellData.getValue().DateProperty());
        PriceCol.setCellValueFactory(cellData -> cellData.getValue().PriceProperty().asString());
        CustomerCol.setCellValueFactory(cellData -> cellData.getValue().CustomerProperty());

        BonTable.setItems(observableSellingBeansList);
        BonTable.setEditable(true);
        BonTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        BonTable.setPlaceholder(new Label("Tabelle ist leer"));

    }//end initialize
    private void update()
    {
        IStorageController sc = new JpaStorageController();

        try
        {
            Main.CRS=sc.loadCashRegisterSystem();
        }
        catch (StorageException ex)
        {
            ex.printStackTrace();
        }
        Cashier currentCashier= Main.CRS.findCashier(CurrentUser.getCurrentUserID());
        Bon currentBon;

        System.out.println(currentCashier.getBon().size());
        for(int i=0; i<currentCashier.getBon().size();i++)
        {


            System.out.println(currentCashier.getBon().size());
            currentBon=currentCashier.getBon().get(i);
            SellingBeans sellingBeans=new SellingBeans();
            sellingBeans.setBonID(currentBon.getId().intValue());
            sellingBeans.setCustomer(currentBon.getCustomer().getLastName()+", "+currentBon.getCustomer().getFirstName());

            //sellingBeans.setDate(Main.dateFormat.format(currentBon.getCreated()));

            float completePrice=0;
            for(int j=0; j<currentCashier.getBon().get(i).getBonInventory().size();j++)
            {
                completePrice+=currentCashier.getBon().get(i).getBonInventory().get(j).getPrice();
            }
            sellingBeans.setPrice(completePrice);

            observableSellingBeansList.add(sellingBeans);

        }
    }

    /*
    handle column edits
     */
    public void CustomerCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<SellingBeans, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<SellingBeans, String>) e;
        SellingBeans SellingBeans = cellEditEvent.getRowValue();
        SellingBeans.setCustomer(cellEditEvent.getNewValue());
    }

    public void handleDeleteButtonClick(ActionEvent event) {
        if(!observableSellingBeansList.isEmpty()) {
            System.out.println("Löschen Button gedrückt");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "OK", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Sind Sie sicher das diese Aktion vortgesetzt werden soll?\n\nAKTION KANN NICHT RÜCKGÄNGIG GEMACHT WERDEN");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                observableSellingBeansList.removeAll(BonTable.getSelectionModel().getSelectedItems());
                BonTable.getSelectionModel().clearSelection();
            }
            else {
                deleteAlert.close();
            }
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent event)throws Exception {
        if(CurrentUser.isAdmin())
        {
            CFObject.switchScene(event,"Admin_Menu.fxml");
        }
        else
        {
            CFObject.switchScene(event,"Employee_Menu.fxml");
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








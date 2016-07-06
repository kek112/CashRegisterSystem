package de.ToDaKa.CashRegisterSystem.control;



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

import javafx.util.converter.NumberStringConverter;
import de.ToDaKa.CashRegisterSystem.model.Beans.*;

/**
 * Created by karls_000 on 23.06.2016.
 */
public class ControllerStockAdvancedTableViewScreen implements Initializable
{


    ControllerFunctions CFobject=new ControllerFunctions();
    @FXML
    private MenuBar fileMenu;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //TABLEVIEW
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TableColumn<StockBeans, String> BarcodeCol1;
    @FXML
    private TableColumn<StockBeans, String> NameCol;
    @FXML
    private TableColumn<StockBeans, Number> AmountCol;
    @FXML
    private TableColumn<StockBeans, Number> PriceCol;
    @FXML
    private TableColumn<StockBeans, String> isFoodCol;
    @FXML
    private TableView<StockBeans> Stocktable;

    ObservableList<StockBeans> observableStockBeansList = FXCollections.observableArrayList();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ADD MENU
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField BarcodeTextfield;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField NameTextfeld;
    @FXML
    private TextField AmountTextfield;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField filterInput;
    @FXML
    private ComboBox<String> isFoodBox;
    ObservableList<String> isFoodBoxData = FXCollections.observableArrayList();


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //INITIALIZE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        filterInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterNameList((String) oldValue,(String) newValue);
            }
        });

        // init edit fields
        Stocktable.setEditable(true);

        BarcodeCol1.setOnEditCommit (e->BarcodeCol_OnEditCommit(e));
        NameCol.setOnEditCommit     (e->NameCol_OnEditCommit(e));
        AmountCol.setOnEditCommit   (e->AmountCol_OnEditCommit(e));
        PriceCol.setOnEditCommit    (e->PriceCol_OnEditCommit(e));
        isFoodCol.setOnEditCommit   (e->isFoodCol_OnEditCommit(e));

        Stocktable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BarcodeCol1.setCellFactory(TextFieldTableCell.  forTableColumn());
        NameCol.setCellFactory(TextFieldTableCell.      forTableColumn());
        AmountCol.setCellFactory(TextFieldTableCell.    forTableColumn(new NumberStringConverter()));
        PriceCol.setCellFactory(TextFieldTableCell.     forTableColumn(new NumberStringConverter()));
        isFoodCol.setCellFactory(TextFieldTableCell.    forTableColumn());

        BarcodeCol1.setCellValueFactory     (cellData->cellData.getValue().BarcodeProperty());
        NameCol.setCellValueFactory         (cellData->cellData.getValue().NameProperty());
        AmountCol.setCellValueFactory       (cellData->cellData.getValue().AmountProperty());
        PriceCol.setCellValueFactory        (cellData->cellData.getValue().PreisProperty());
        isFoodCol.setCellValueFactory       (cellData->cellData.getValue().isFoodProperty());


        //initialize ComboBox
        isFoodBoxData.add(new String("Ja"));
        isFoodBoxData.add(new String("Nein"));

        isFoodBox.setItems(isFoodBoxData);

        addBtn.setDisable       (true);
        deleteBtn.setDisable    (true);
        Stocktable.setItems     (observableStockBeansList);
        Stocktable.setEditable  (true);

        Stocktable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Stocktable.setPlaceholder(new Label("Keine Einträge vorhanden"));

        BarcodeTextfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
             if(BarcodeTextfield.isFocused())
             {
                addBtn.setDisable(false);
             }
            }
        });
        AmountTextfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(AmountTextfield.isFocused())
                {
                    addBtn.setDisable(false);
                }
            }
        });
        Stocktable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(Stocktable.isFocused())
                {
                    deleteBtn.setDisable(false);
                }
            }
        });
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HANDLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleSave()
    {
        Stage secondaryStage=new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Inventar abspeichern");
      //  fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableStockBeansList.isEmpty())
        {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "Tabelle leer", ButtonType.OK);
            emptyTableAlert.setContentText("keine Daten vorhanden");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK)
            {
                emptyTableAlert.close();
            }
        }
        else
        {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null)
            {
                saveFile(Stocktable.getItems(), file);
            }
        }
    }

    @FXML
    private void handleBack()
    {
        System.out.println("Handle Back pressed");
    }
    @FXML
    private void handleBackButton(ActionEvent e) throws Exception
    {
       CFobject.switchScene(e,"EmployeeAdvancedTableView_Screen.fxml");

       /* Parent nextScene = (Parent) FXMLLoader.load(getClass().getResource("FoodAdvancedTableView_Screen.fxml"));
        Scene scene = new Scene(nextScene);
        Node node = (Node)e.getSource();
        Stage sStage =  (Stage) node.getScene().getWindow();
        sStage.setScene(scene);
        sStage.show();*/

    }



    //////////////////////////////////////////////////
    //HANDLE column edits
    //////////////////////////////////////////////////


    @FXML
  private  void BarcodeCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<StockBeans,String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<StockBeans,String>) e;
        StockBeans stockBeans = cellEditEvent.getRowValue();

        stockBeans.setBarcode((String) cellEditEvent.getNewValue());

    }

    @FXML
    private void NameCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<StockBeans,String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<StockBeans,String>) e;
        StockBeans stockBeans = cellEditEvent.getRowValue();
        stockBeans.setName(cellEditEvent.getNewValue());

        StockBeans stockrow= (StockBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<StockBeans, String>) e).getTablePosition().getRow());

        System.out.println(stockrow.getBarcode());
    }

    @FXML
    private void AmountCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<StockBeans,Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<StockBeans,Number>) e;
        StockBeans stockBeans = cellEditEvent.getRowValue();
        stockBeans.setAmount(cellEditEvent.getNewValue().intValue());

        StockBeans stockrow= (StockBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<StockBeans, Number>) e).getTablePosition().getRow());

    }

    @FXML
    private void PriceCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<StockBeans,Number> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<StockBeans,Number>) e;
        StockBeans stockBeans = cellEditEvent.getRowValue();
        stockBeans.setPrice(cellEditEvent.getNewValue().doubleValue());

        StockBeans stockrow= (StockBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<StockBeans, Number>) e).getTablePosition().getRow());
    }

    @FXML
    private void isFoodCol_OnEditCommit(Event e)
    {
        TableColumn.CellEditEvent<StockBeans,String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<StockBeans,String>) e;
        StockBeans stockBeans = cellEditEvent.getRowValue();
        stockBeans.setIsFood((String) cellEditEvent.getNewValue());

        StockBeans stockrow= (StockBeans) cellEditEvent.getTableView().getItems().get(((TableColumn.CellEditEvent<StockBeans, String>) e).getTablePosition().getRow());
    }
    //////////////////////////////////////////////////
    //HANDLE Buttons
    //////////////////////////////////////////////////
    @FXML
    private void handleAddButtonClick(ActionEvent event)
    {
        if(isValidInput(event))
        {
            if(isFoodBox.getValue().equals("Ja"))
            {

                StockBeans stockBeans = new StockBeans();
                stockBeans.setName   (   NameTextfeld.getText());
                stockBeans.setBarcode(   BarcodeTextfield.getText());
                stockBeans.setAmount (   Integer.parseInt(AmountTextfield.getText()) );
                stockBeans.setPrice  (   Float.parseFloat(PriceField.getText())      );
                stockBeans.setIsFood (   isFoodBox.getValue());

                observableStockBeansList.add(stockBeans);
                System.out.println(stockBeans.printString());


                BarcodeTextfield.clear();
                AmountTextfield.clear();
                PriceField.clear();
                NameTextfeld.clear();
                isFoodBox.setValue("Lebensmittel");


            }
            if(isFoodBox.getValue().equals("Nein"))
            {
                StockBeans stockBeans = new StockBeans();
                stockBeans.setName   (   NameTextfeld.getText());
                stockBeans.setBarcode(   BarcodeTextfield.getText());
                stockBeans.setAmount (   Integer.parseInt(AmountTextfield.getText()) );
                stockBeans.setPrice  (   Float.parseFloat(PriceField.getText())      );
                stockBeans.setIsFood (   isFoodBox.getValue());

                observableStockBeansList.add(stockBeans);
                System.out.println(stockBeans.printString());

                BarcodeTextfield.clear();
                AmountTextfield.clear();
                PriceField.clear();
                NameTextfeld.clear();
                isFoodBox.setValue("Lebensmittel");
            }
        }
    }

    @FXML
    private void handleDeleteButtonClick(ActionEvent event)
    {
        if(!observableStockBeansList.isEmpty())
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
                observableStockBeansList.removeAll(Stocktable.getSelectionModel().getSelectedItems());
                Stocktable.getSelectionModel().clearSelection();
            }
            else
            {
                deleteAlert.close();
            }
        }

    }

    @FXML
    private void handleClearButtonClick(ActionEvent event)
    {
        BarcodeTextfield.clear();
        AmountTextfield.clear();
        PriceField.clear();
        NameTextfeld.clear();
        isFoodBox.setValue("Lebensmittel");
    }

///////////// Sortieren der Namen


    private void filterNameList(String oldValue,String newValue)
    {
        ObservableList<StockBeans> filteredList = FXCollections.observableArrayList();

        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null)
        {
            Stocktable.setItems(observableStockBeansList);
        }
        else
        {
            newValue = newValue.toUpperCase();
            for(StockBeans items : Stocktable.getItems())
            {
                String filterName = items.getName();

                if(filterName.toUpperCase().contains(newValue) )
                {
                    filteredList.add(items);
                }
            }

            Stocktable.setItems(filteredList);
        }

    }


    private void saveFile(ObservableList<StockBeans> observableStockBeansList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(StockBeans stockBeans : observableStockBeansList) {
                outWriter.write(stockBeans.printString());
                outWriter.newLine();
            }
            System.out.println(observableStockBeansList.toString());
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
    private boolean isValidInput(ActionEvent event)
   {
       Boolean validInput = true;

       //Check BArcode for empty
       if(BarcodeTextfield == null || BarcodeTextfield.getText().trim().isEmpty())
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
               BarcodeTextfield.requestFocus();
           }
       }


       //Check Name  for empty
       if(NameTextfeld == null || NameTextfeld.getText().trim().isEmpty())
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
               NameTextfeld.requestFocus();
           }
       }


       //Check  Amount for empty
       if(AmountTextfield == null || AmountTextfield.getText().trim().isEmpty())
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
               AmountTextfield.requestFocus();
           }
       }
       //Check  Amount for empty
       if(PriceField == null || PriceField.getText().trim().isEmpty())
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
               PriceField.requestFocus();
           }
       }

       //Check  isFood for empty
       if(isFoodBox == null || isFoodBox.getValue().isEmpty())
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
               isFoodBox.requestFocus();
           }
       }


       return validInput;


   }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

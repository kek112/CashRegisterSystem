package de.ToDaKa.CashRegisterSystem;

import de.ToDaKa.CashRegisterSystem.model.*;
import de.ToDaKa.CashRegisterSystem.model.execptions.BonExistsException;
import de.ToDaKa.CashRegisterSystem.storage.*;
import de.ToDaKa.CashRegisterSystem.storage.core.*;
import de.ToDaKa.CashRegisterSystem.storage.exception.StorageException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    public static CashRegisterSystem CRS;


    public static void main(String[] args) throws BonExistsException {

        //Create Test CRS
        DataProvider dataProvider =new DataProvider();
        CRS=dataProvider.creatTestCRS();

        Inventory currentArticle;
        IGenericDao<Inventory> InventoryDao =DataController.getInstance().getInventoryDao();
        currentArticle = CRS.findInventory(Long.parseLong("4012362044208"));

        Bon currentBon;
        currentBon=CRS.addBon(new Bon());

        BonInventory currentBonInventory =new BonInventory(2, currentBon, currentArticle);

        currentArticle.addBonInventory(currentBonInventory);
        currentArticle.addBonInventory(currentBonInventory);

        IStorageController sc = new JpaStorageController();

        try
        {
            sc.saveCashRegisterSystem(CRS);
        }
        catch (StorageException e)
        {
            e.printStackTrace();
        }
        //search by ID
        IGenericDao<Customer> CustomerDao = DataController.getInstance().getCustomerDao();
        IGenericDao<Cashier> CashierDao = DataController.getInstance().getCashierDao();


        Customer searchedCustomer= CustomerDao.findById(new Long(1));
        System.out.println( searchedCustomer.getId() +" - "+searchedCustomer.getFirstName() );


        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent LoginPane;

        primaryStage.setTitle("ToDaKa-Kassensystem");
        LoginPane = (Parent)  FXMLLoader.load(getClass().getClassLoader().getResource("de/ToDaKa/CashRegisterSystem/view/Login_Screen.fxml"));
        Scene scene = new Scene(LoginPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


  }
package de.ToDaKa.CashRegisterSystem.control;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 Class: ControllerAdminMenu
 @author Karl-Erik Kley

 Provides the connection for the FXML window,
 to create the interface to the system
 **/
public class ControllerAdminMenu
{

    ControllerFunctions CFObject=new ControllerFunctions();

    @FXML
    TextField TextfieldNameLoginScreen;

    @FXML
    PasswordField TextfieldPasswordLoginScreen;

    @FXML
    Button BuyButton;

    @FXML
    Button CustomerButton;

    @FXML
    Button InventarButton;

    @FXML
    Button CashierButton;

    @FXML
    Button SellingsButton;

    @FXML
    Button StatistikButton;

    @FXML
    Button LogoutButton;


    @FXML
    private void handleBuyButtonClick(ActionEvent event)throws Exception
    {
            CFObject.switchScene(event,"CustomerBuy_Screen.fxml");

    }

    @FXML
    private void handleCustomerButtonClick(ActionEvent event)throws Exception
    {
            CFObject.switchScene(event,"Customer_Screen.fxml");


    }


    @FXML
    private void handleInventarButtonClick(ActionEvent event)throws Exception
    {
            CFObject.switchScene(event,"StockAdvancedTableView_Screen.fxml");
    }
    @FXML
    private void handleCashierButtonClick(ActionEvent event)throws Exception
    {
            CFObject.switchScene(event,"EmployeeAdvancedTableView_Screen.fxml");


    }
    @FXML
    private void handleSellingsButtonClick(ActionEvent event)throws Exception
    {
        CFObject.switchScene(event,"SellingsTableView_Screen.fxml");

    }
    @FXML
    private void handleStatistikButton(ActionEvent event)throws Exception
    {

        CFObject.switchScene(event,"Statistic_Popup.fxml");


    }
    @FXML
    private void handleLogoutButtonClick(ActionEvent event)throws Exception
    {
            CFObject.switchScene(event,"Login_Screen.fxml");

    }

}

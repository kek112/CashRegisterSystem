package de.ToDaKa.CashRegisterSystem.control;


import de.ToDaKa.CashRegisterSystem.CurrentUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by karls_000 on 20.06.2016.
 */
public class ControllerEmployeeMenu
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
    private void handleLogoutButtonClick(ActionEvent event)throws Exception
    {
        CurrentUser.reset();
        CFObject.switchScene(event,"Login_Screen.fxml");

    }

}

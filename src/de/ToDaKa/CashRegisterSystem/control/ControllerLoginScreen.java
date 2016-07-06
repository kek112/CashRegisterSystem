package de.ToDaKa.CashRegisterSystem.control;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by karls_000 on 20.06.2016.
 */
public class ControllerLoginScreen
{

    ControllerFunctions CFObject=new ControllerFunctions();

    @FXML
    TextField TextfieldNameLoginScreen;

    @FXML
    PasswordField TextfieldPasswordLoginScreen;

    @FXML
    Button ButtonExitLoginScreen;

    @FXML
    Button ButtonLoginLoginScreen;

    @FXML
    private void handleButtonEventExit()
    {
            Platform.exit();
    }

    @FXML
    private void handleButtonEventLogin(ActionEvent event)
    {
        try
        {
            if (checkuser(TextfieldNameLoginScreen.getText(),TextfieldPasswordLoginScreen.getText()))
            {
              System.out.println(CFObject.switchScene(event,"CustomerBuy_Screen.fxml"));
            }
            else
            {

            }
        }
        catch(Exception Ee)
        {
            System.out.print(Ee.getMessage());
        }
    }

    boolean checkuser(String username,String password)
    {
        //getMD5();
        return true;
    }


}

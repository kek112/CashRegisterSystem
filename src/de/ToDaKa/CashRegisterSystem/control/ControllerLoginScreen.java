package de.ToDaKa.CashRegisterSystem.control;


import de.ToDaKa.CashRegisterSystem.CurrentUser;
import de.ToDaKa.CashRegisterSystem.MD5;
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
    private void handleButtonEventLogin(ActionEvent event)throws Exception
    {
            if (checkuser(TextfieldNameLoginScreen.getText(),TextfieldPasswordLoginScreen.getText()))
            {
                CurrentUser.setCurrentUserID(Integer.parseInt(TextfieldNameLoginScreen.getText()));
                if(CurrentUser.isAdmin())
                {
                    CFObject.switchScene(event,"Admin_Menu.fxml");
                }
                else
                {
                    CFObject.switchScene(event,"Employee_Menu.fxml");
                }
            }
            else
            {

            }

    }

    boolean checkuser(String username,String password)
    {
        String MD5Pass;
        MD5Pass=MD5.getMD5(password);



        if(username.isEmpty())
        {

            return false;
        }
        else
        {
            return true;
        }
        //getMD5();
    }


}

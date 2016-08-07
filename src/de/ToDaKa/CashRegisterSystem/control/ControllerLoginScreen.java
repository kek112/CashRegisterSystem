package de.ToDaKa.CashRegisterSystem.control;


import de.ToDaKa.CashRegisterSystem.CurrentUser;
import de.ToDaKa.CashRegisterSystem.MD5;
import de.ToDaKa.CashRegisterSystem.Main;
import de.ToDaKa.CashRegisterSystem.model.Cashier;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.sql.rowset.CachedRowSet;

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
            if (checkuser(Integer.parseInt(TextfieldNameLoginScreen.getText()),TextfieldPasswordLoginScreen.getText()))
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

    boolean checkuser(long UserID,String password)
    {
        String MD5Pass;
        MD5Pass=MD5.getMD5(password);
        Cashier currentCashier=Main.CRS.findCashier(UserID);
        if(currentCashier!=null)
        {
           if(currentCashier.getMd5Password().equals(MD5Pass)==true)
            {
                CurrentUser.setCurrentUserID(currentCashier.getId());
                return true;
            }
            else
            {
             return false;
            }
        }
        else
        {
            return false;
        }
        //getMD5();
    }


}

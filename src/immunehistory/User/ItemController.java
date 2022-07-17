/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class ItemController implements Initializable {
  static String serialno,name,consultid,apdate,condate,stat;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label serial_no;
    @FXML
    private Label consult_id;
    @FXML
    private Label doctorname;
    @FXML
    private Label appointing_date;
    @FXML
    private Label consulting_date;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serial_no.setText(serialno);
        consult_id.setText(consultid);
        doctorname.setText(name);
        appointing_date.setText(apdate);
        consulting_date.setText(condate);
       
        
    }    
    public void myFunction(String Text)
    {
        
    }
}

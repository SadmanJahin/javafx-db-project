/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package immunehistory.User;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import immunehistory.User.ItemController;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Sadman
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String userId;
     Image image = null;
    static String  finalName,finalPhone;
    @FXML
    private VBox appointmentlist=null;
    @FXML
    private Button overviewbtn;

    @FXML
    private Button makebtn;

    @FXML
    private Button appointmentbtn;

    @FXML
    private Button prescriptionbtn;

    @FXML
    private Button historybtn;

    @FXML
    private Button signoutbtn;

    @FXML
    private Pane overviewPane;

    @FXML
    private Pane makeAppointmentPane;
    
     @FXML
    private Pane appointmentPane;

    @FXML
    private Pane prescriptionPane;

   
    
   
    public Connection connection;
    @FXML
    private Label UserName;
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField mobilefield;
    @FXML
    private TextField dateofbirthfield;
    @FXML
    private TextArea addressfield;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_update;
    @FXML
    private TextField zipfield;
    @FXML
    private TextField genderfield;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private StackPane sidePane;
    @FXML
    private VBox navBar;
    @FXML
    private Ellipse userPhoto;
    @FXML
    private Button btn_upload;
    static TextField problem_field;
    @FXML
    private VBox tagBox;
    @FXML
    private TextField doctor_field;
    @FXML
    private CheckBox checkSuggestion;
    
    
    //ArrayList<String>  doctorSuggestion=new ArrayList<String>();
    Set<String> doctorSuggestion = new HashSet<>();
SuggestionProvider<String> provider;

    Set<String> filteredAutoCompletions = new HashSet<>(Arrays.asList("A", "B"));



    @FXML
    private CheckBox checkProblems;
    @FXML
    private Button btn_details;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
      


        UserName.setText(finalName);
        
        
       ConnectMSSQL cnObj = new ConnectMSSQL();
       
       
      Object[] docObj=cnObj.fetchDoctorList();
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
       
       doctorName=(ArrayList)docObj[0];
       degree=(ArrayList)docObj[1];
       
      
        for(int i=0;i<doctorName.size();i++)
        {
            doctorSuggestion.add(doctorName.get(i)+" "+degree.get(i));
        }
         //autoCompletions=(Set<String>) doctorSuggestion;
        
        //TextFields.bindAutoCompletion(doctor_field, doctorSuggestion);
        
       
       BoxBlur blur=new BoxBlur(3,3,3);
 JFXDialogLayout content= new JFXDialogLayout();
content.setHeading(new Text("Info"));
content.setBody(new Text("Please Set Up The Form"));
StackPane stackpane = new StackPane();
JFXDialog dialog =new JFXDialog(sidePane, content, JFXDialog.DialogTransition.CENTER);
JFXButton button=new JFXButton("Okay");
button.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
        dialog.close();
       overviewPane.setEffect(null);
    }
});
content.setActions(button);
dialog.show();
overviewPane.setEffect(blur);
      /* JFXButton button=new JFXButton("Ok");
       button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
        dialog.close();   
       }
       );
       JFXDialogLayout dialoglayout=new JFXDialogLayout();
       dialoglayout.setBody(new Text("PLEASE FILL UP YOUR DATA"));
       JFXDialog dialog =new JFXDialog(sidePane, dashboardPane, JFXDialog.DialogTransition.CENTER);*/
       ArrayList<String> data=cnObj.fetchData(finalPhone);
       
       userId=data.get(0);
       usernamefield.setText(data.get(1));
       emailfield.setText(data.get(2));
       mobilefield.setText(data.get(3));
       dateofbirthfield.setText(data.get(4));
       genderfield.setText(data.get(5));
       addressfield.setText(data.get(6));
       zipfield.setText(data.get(7));
       String image_url=data.get(8);
       Image userImage = new Image(getClass().getResourceAsStream(image_url));
       userPhoto.setStroke(Color.web("#818a84"));
       userPhoto.setFill(new ImagePattern(userImage));
       userPhoto.setEffect(new DropShadow(+25d,0d,2d,Color.web("#818a84")));
       //Query 1
       
       int rowNum=cnObj.rowList("SELECT COUNT (ap_sl_no) as row FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id");
       
       //Query2
      Object[] obj ;
      obj= cnObj.showAppointment("SELECT ap_sl_no,appointment.consult_id,doctor_name,FORMAT (date_of_appointment_taken, 'dd-MM-yy')AS date_of_appointment_taken,FORMAT (date_of_consult, 'dd-MM-yy')AS date_of_consult,appoint_active FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id");
      
      
      ArrayList<String> SLno = new ArrayList<String>();
       SLno=(ArrayList)obj[0];
      ArrayList<String> ConsultId = new ArrayList<String>();
      ConsultId=(ArrayList)obj[1];
     ArrayList<String> DoctorName = new ArrayList<String>();
     DoctorName=(ArrayList)obj[2];
     ArrayList<String>  AppointmentDate= new ArrayList<String>();
     AppointmentDate=(ArrayList)obj[3];
     ArrayList<String> ConsultDate = new ArrayList<String>();
     ConsultDate=(ArrayList)obj[4];
     ArrayList<String> Status = new ArrayList<String>();
     Status=(ArrayList)obj[5];        
             Node[] nodes= new Node[rowNum];
            int i=0;
            for(i=0;i<rowNum;i++)
            {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Item.fxml"));
                try {
                    Parent root = (Parent) loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            ItemController secController=loader.getController();
            secController.serialno=SLno.get(i);
            secController.name=DoctorName.get(i);
            secController.consultid=ConsultId.get(i);
            secController.apdate=AppointmentDate.get(i);
            secController.condate=ConsultDate.get(i);
            secController.stat=Status.get(i);
            secController.initialize(url, rb);
                try {
                    nodes[i]=(Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                    final int j = i;
                     nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #9C9AA6");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #FFF");
                });
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                appointmentlist.getChildren().add(nodes[i]);
            }
             
            /* Node[] nodes= new Node[rowNum];
            int i=0;
            while (resultSet.next()) {
                
                
                 try {
                 try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Item.fxml"));
            Parent root = (Parent) loader.load();

            ItemController secController=loader.getController();
            secController.serialno=resultSet.getString("ap_sl_no");
            secController.name=resultSet.getString("doctor_name");
            secController.consultid=resultSet.getString("consult_id");
            secController.apdate=resultSet.getString("date_of_appointment_taken");
            secController.condate=resultSet.getString("date_of_consult");
            secController.stat=resultSet.getString("appoint_active");
            secController.initialize(url, rb);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
                nodes[i]=(Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                appointmentlist.getChildren().add(nodes[i]);
                i++;
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    tagBox.getChildren().addAll(tagBar);
        
        
        
    }    
    @FXML
     public void handleClicks(ActionEvent actionEvent) throws MalformedURLException, IOException {
        if (actionEvent.getSource() == prescriptionbtn ) {
            prescriptionPane.setStyle("-fx-background-color : #53639F");
            prescriptionPane.toFront();

        }
        if (actionEvent.getSource() == overviewbtn ) {
            overviewPane.toFront();
        }
        if (actionEvent.getSource() == appointmentbtn ) {
            appointmentPane.setStyle("-fx-background-color : #FFFF");
            appointmentPane.toFront();
        }
         if (actionEvent.getSource() == signoutbtn ) {
            logoutSuccess();
        }
         
         
         if (actionEvent.getSource() == btn_edit )
         {
             btn_edit.setVisible(false);
              btn_update.setVisible(true);
              btn_upload.setVisible(true);
              usernamefield.setEditable(true);
       emailfield.setEditable(true);
       mobilefield.setEditable(true);
       dateofbirthfield.setEditable(true);
       genderfield.setEditable(true);
       addressfield.setEditable(true);
       zipfield.setEditable(true);
         }
        
         if (actionEvent.getSource() == btn_upload )
         {
                 FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); 
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
        String imagepath = file.toURI().toURL().toString();
        System.out.println("file:"+imagepath);
         image = new Image(imagepath);
       userPhoto.setStroke(Color.web("#818a84"));
       userPhoto.setFill(new ImagePattern(image));
       userPhoto.setEffect(new DropShadow(+25d,0d,2d,Color.web("#818a84")));
    }
    else
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please Select a File");
        /*alert.setContentText("You didn't select a file!");*/
        alert.showAndWait();
    }

         }
          if (actionEvent.getSource() == btn_update )
         {
       btn_edit.setVisible(true);
       btn_update.setVisible(false);
       btn_upload.setVisible(false);
       usernamefield.setEditable(false);
       emailfield.setEditable(false);
       mobilefield.setEditable(false);
       dateofbirthfield.setEditable(false);
       genderfield.setEditable(false);
       addressfield.setEditable(false);
       zipfield.setEditable(false);
       
       File outputfile = new File("src/immunehistory/User/userimages/"+userId+".png");
         BufferedImage BI=SwingFXUtils.fromFXImage(image, null);
          ImageIO.write(BI, "png",outputfile);
       String imgURL="userimages/"+userId+".png";
       ConnectMSSQL cnObj = new ConnectMSSQL();
            try {
                cnObj.updateData(usernamefield.getText(), emailfield.getText(), mobilefield.getText(), dateofbirthfield.getText(), genderfield.getText(), addressfield.getText(), zipfield.getText(),imgURL,finalPhone);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
    }
       private double x, y;
 void logoutSuccess()
 {
      try {
            
           
            
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            
           //control.passeddata("hello", "111");
            
            
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
  
               Scene scene=new Scene(root);
               stage.setScene(scene);
               new animatefx.animation.BounceInRight(root).play();
                stage.show();
            
             
                root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                
            });
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
 }
TagBar tagBar = new TagBar();
    private void keyPressCheck(KeyEvent event) {
         String s=tagBar.getText();
        if(event.getCode()==KeyCode.ENTER)
         tagBar.getTags().add(s);
    }

    @FXML
    private void handleCheckbox(ActionEvent event) {
        
        checkProblems.setDisable(false);
         btn_details.setDisable(false);
         
        if(checkSuggestion.isSelected())
        {
             provider= SuggestionProvider.create(doctorSuggestion);
            new AutoCompletionTextFieldBinding<>(doctor_field, provider);
       
      //provider.addPossibleSuggestions(filteredAutoCompletions);

        }
        else
        {
           provider.clearSuggestions();
            checkProblems.setDisable(true);
            btn_details.setDisable(true);
        }
        if(checkProblems.isSelected())
        {
           // TextFields.bindAutoCompletion(doctor_field, "qq");
        }
    }
    
}

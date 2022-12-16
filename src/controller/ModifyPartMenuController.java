/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class for Modify part menu.
 * HAs methods for menu for modify part screen that will update any part and insert into inventory
 * and print errors if any issues with updating part. 
 *
 * @author tjkapil
 */
public class ModifyPartMenuController implements Initializable {
    /**
     * to create alerts to be displayed to user
     */
     Alert a = new Alert(Alert.AlertType.CONFIRMATION);
     
    /**
     * to hold part of Type Part selected by user to modify
     */
    private Part PartSelectedByUser;
    
    /**
     * Button for canceling modify part and going back to main screen. 
     */
    @FXML
    private Button CancelOnClick;
    /**
     * text field for inventory amount
    @FXML
    private TextField InvPartTextField;
    /**
    * text field for name field 
    */
    @FXML
    private TextField NamePartTextField;
    /**
     * text field for price
     */
    @FXML
    private TextField PriceMaxTextField;
    /**
     * text field for min
     */
    @FXML
    private TextField PriceMinTextField;
    /**
     * text field for price
     */
    @FXML
    private TextField PricePartTextField;
    
    /**
     * button for saving modified part
     */
    @FXML   
    private Button SaveOnClick;

    /**
     * label for company name and machine id switching 
     */
    @FXML
    private Label SwitchMachineID;
    
    /**
     * text field for id. 
     */
    @FXML
    private TextField idPartTextField;
    
    /**  
     * text-field for in-house radio button
     */
    @FXML
    private RadioButton inHouseRadio;
    /**
     * textfield for outsourced radio button 
     */
    @FXML
    private RadioButton outSourcedRadio;
    
    /**
     * toggle group to switch selection between buttons. 
     */
    @FXML
    private ToggleGroup partGroup;
    /**
     * text field for inventory stock
     */
    @FXML
    private TextField InvPartTextField;
    
    /**
     * text-field for machine id switching
     */
    @FXML
    private TextField SwtichMachineIdField;

    /**
     * Initializes the controller class and sets information that is original to be modified
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PartSelectedByUser = MainMenuController.getUserSelectedPart(); // hold part selected by user to be modifed
        
        if(PartSelectedByUser instanceof Outsourced outsourced) { // if it is an outsourced part selected by iser
            outSourcedRadio.setSelected(true); // set selected outsourced toggle to true
            SwitchMachineID.setText("Company Name"); // set the text to company name
            SwtichMachineIdField.setText(outsourced.getCompanyName()); // set the company name       
        }
        
        if(PartSelectedByUser instanceof InHouse inHouse) { // if part selected by user is an in house part
            inHouseRadio.setSelected(true); // set inhouse to true
            SwitchMachineID.setText("Machine Id"); // set label to machine id
            SwtichMachineIdField.setText(String.valueOf(inHouse.getMachineId())); // set text of machine id     
        }
        
        
        /**
         * set all of the parts attributes so that they can be displayed when modifying. 
         */
        idPartTextField.setText(String.valueOf(PartSelectedByUser.getId()));
        NamePartTextField.setText(PartSelectedByUser.getName());
        InvPartTextField.setText(String.valueOf(PartSelectedByUser.getStock()));
        PricePartTextField.setText(String.valueOf(PartSelectedByUser.getPrice()));
        PriceMaxTextField.setText(String.valueOf(PartSelectedByUser.getMax()));
        PriceMinTextField.setText(String.valueOf(PartSelectedByUser.getMin()));
        
    }    
    
    /**
     * if in-house radio is selected switch label to machine id
     * @param event of type ActionEvent
     */
    public void SelectInHouseRadio(ActionEvent event) {
        SwitchMachineID.setText("Machine ID");
    }

    /**
     * if outsourced radio is selected switch label to company name
     * @param event event of type ActionEvent
     */
    public void SelectOutsourcedRadio(ActionEvent event) {
        SwitchMachineID.setText("Company Name");
    }
    /**
     * check if inv is within range and if minimum amount if valid, 
     * print errors if it is not. 
     * @param maxCheck
     * @param minCheck
     * @param stockCheck
     * @return boolean
     */
    private boolean isInvValid(int maxCheck, int minCheck, int stockCheck) {
        boolean check = true;
        
        if (stockCheck < minCheck || stockCheck > maxCheck) {
            check = false;
            printError(4);
        }
        
        if (minCheck <= 0 || minCheck >= maxCheck) {
            check = false;
            printError(3);
        }
        
        return check;
    }
    
    // private boolean isMinValid(int maxCheck, int minCheck) {
    //    boolean check = true;
    //    if (minCheck <= 0 || minCheck >= maxCheck) {
    //        check = false;
    //        printError(3);
    //    }
   //     return check;
   // }
    /**
     * print errors, method to be used in other methods
     * @param type of int
     */
    private void printError(int type) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert e = new Alert(Alert.AlertType.ERROR);

        switch (type) {
            case 1 -> {
                a.setTitle("User Error!");
                a.setHeaderText("This part can't be modified");
                a.setContentText("Please fix blank fields or values that are invalid");
                a.showAndWait();
                break;
            }
            case 2 -> {
                a.setTitle("User Error!");
                a.setHeaderText("Entered Machine ID is not valid");
                a.setContentText("The Machine ID can only have numbers.");
                a.showAndWait();
                break;
            }
            case 3 -> {
                e.setTitle("User Error!");
                e.setHeaderText("Entered minimum value is not valid");
                a.setContentText("The min must be > than 0 and < than max.");
                e.showAndWait();
                break;
            }
            case 4 -> {
                e.setTitle("User Error!");
                e.setHeaderText("Entered inventory value is invalid");
                e.setContentText("Inventory value must be = to or between min and max values.");
                e.showAndWait();
                break;
            }
        }
    }
    /**
     * method used to cancel modified changes and ignore them and head to main screen
     * @param event
     * @throws IOException 
     */
    public void CancelOnClick(ActionEvent event) throws IOException {
        a.setTitle("User Confirmation"); // set title pf box
        a.setContentText("Are you sure you want to head to main screen and ignore changes?");
        Optional<ButtonType> r = a.showAndWait(); // wait for user response
        
        if(r.isPresent() && r.get()== ButtonType.OK) { // when user clicks button
            Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // set up screen and stage and go to main menu
        }
    }

    /**
     * method to save all modified changes to array list and display on main menu
     * table view, if any problems that indicate using user alerts. 
     * @param event
     * @throws IOException 
     */
    public void SaveOnClick(ActionEvent event) throws IOException {
        try {
            int partId = PartSelectedByUser.getId(); // get id of part
            int partMax = Integer.parseInt(PriceMaxTextField.getText()); // get max amount inv
            int partStock = Integer.parseInt(InvPartTextField.getText()); // get inventory amount
            Double partPrice = Double.parseDouble(PricePartTextField.getText()); // get part price
            String partName = NamePartTextField.getText(); // get part name
            int partMin = Integer.parseInt(PriceMinTextField.getText()); // get min amount inv
            String nameOfCompany; // for storing name of company
            boolean successIfPartAdded = false; // for indicating if part modified and added
            int IdMachineField; // for storing machine id
            
            if (isInvValid(partMax, partMin, partStock)) { // check if within range
                    if(inHouseRadio.isSelected()) { // if in range and in house selected
                        try {
                           IdMachineField = Integer.parseInt(SwtichMachineIdField.getText()); // switch machine id label
                           InHouse addInHousePart = new InHouse(partId, partName, partPrice, partStock, partMin, partMax, IdMachineField);
                           addInHousePart.setId(Inventory.createNewIdPart()); // create new modified part
                           Inventory.addPart(addInHousePart); // add part to inventory
                           successIfPartAdded = true; // when added then set to true
                        } catch (Exception e) { // if issues
                            printError(2); // print error 
                        }
                    }
                    if(outSourcedRadio.isSelected()) { // if out-sourced radio selected
                        nameOfCompany = SwtichMachineIdField.getText(); // get company name field
                        Outsourced addOutsourcedPart = new Outsourced(partId, partName,partPrice, partStock, partMin, partMax, nameOfCompany);
                        Inventory.addPart(addOutsourcedPart); // create new part and add to inventory
                        successIfPartAdded = true; // set to equal to true if added
                    }
                    if(successIfPartAdded = true) { // if added then we head to main screen
                      Inventory.deletePart(PartSelectedByUser);
                      Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                      Scene s = new Scene(p);
                      Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      st.setScene(s);
                      st.show(); // set stage and scene to head to main screen
                    }
                }
                   
        } catch(Exception e) { // if any issues
            printError(1); // print error 
    }
    }
}
    


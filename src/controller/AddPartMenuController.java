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

/**
 * FXML Controller class for AddPartMenu
 * this class will contain methods to handle the menu for adding a part to the table
 * view so that the viewers can add a new part. 
 * @author tjkapil
 */
public class AddPartMenuController implements Initializable {
    /**
     * used to create alerts on screen 
     */
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    /**
     * Button to cancel adding part and head to main screen
     */
    @FXML
    private Button CancelOnClick;
    /**
     * text field to enter in inventory stock number
     */
    @FXML
    private TextField InvPartTextField;
     /**
     * text field to enter in part name
     */
    @FXML
    private TextField NamePartTextField;
    /**
     * text field to enter in part max price
     */
    @FXML
    private TextField PriceMaxTextField;
    /**
     * text field to enter in part min price
     */
    @FXML
    private TextField PriceMinTextField;
    /**
     * text field to enter in machine id or company name
     */
    @FXML
    private TextField swtichmachineidfield;
    /**
     * text field to enter in part price
     */
    @FXML
    private TextField PricePartTextField;
    /**
     * text field to save data typed by user
     */
    @FXML
    private Button SaveOnClick;
    /**
     * label for switching machine id to company name and back. 
     */
    @FXML
    private Label SwitchMachineID;
    /**
     * label for text field for id of part
     */
    @FXML
    private TextField idPartTextField;
    /**
     * radio button for in-house part
     */
    @FXML
    private RadioButton inHouseRadio;
    /**
     * radio button for outsourced part
     */
    @FXML
    private RadioButton outSourcedRadio;
    /**
     * toggle group to toggle between out-sourced and in-house part
     */
    @FXML
    private ToggleGroup partGroup;


    /**
     * Initializes the controller class for adding part and also sets the toggle button for
     * in-house radio to be selected already on menu launch. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inHouseRadio.setSelected(true); // set to equal true at start 
    } 
    
    /**
     * If in-house radio is selected than the label will say Machine id
     * and take in a integer. 
     * @param event 
     */
    public void SelectInHouseRadio(ActionEvent event) {
        SwitchMachineID.setText("Machine ID");
    }

    /**
     * If out-sourced radio is selected than the label will say company name
     * and take in a String. 
     * @param event 
     */
    public void SelectOutsourcedRadio(ActionEvent event) {
        SwitchMachineID.setText("Company Name");
    }
    
    /**
     * When user clicks cancel changes will be ignored and they will go to main screen
     * @param event
     * @throws IOException 
     */
    public void CancelOnClick(ActionEvent event) throws IOException {
        a.setTitle("User Confirmation"); // set title of dialog box
        a.setContentText("Are you sure you want to head to main screen and ignore changes?"); // set content of dialog box 
        Optional<ButtonType> r = a.showAndWait(); // wait for user response
        
        if(r.isPresent() && r.get()== ButtonType.OK) { // when iser clicks button
            Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // set stage and scene up for main menu screen. 
        }
    }
    
    /**
     * When save is clicked the new part the user added will be show up in the inventory
     * it can then be seen in the table view on the main screen. IF anything is invalid in the 
     * entries in fields the user will be alerted. 
     * @param event
     * @throws IOException 
     */
    public void saveOnClick(ActionEvent event) throws IOException {
        try {
            int partId = 0;     
            Double partPrice = Double.parseDouble(PricePartTextField.getText()); // get text from price field           
            int partMax = Integer.parseInt(PriceMaxTextField.getText()); // get text from max field
            String partName = NamePartTextField.getText(); // get text from name field
            int partMin = Integer.parseInt(PriceMinTextField.getText()); // get text from min field
            int IdMachineField; // to store machinefield text int
            int partStock = Integer.parseInt(InvPartTextField.getText()); // get text from stock field
            String nameOfCompany; // to store machine field string text
            boolean successIfPartAdded = false; // to be used to indicate if part was added
            
            if (partName.length() == 0) { // if the length of the name is zero, then no name has been entered when adding part.
                // thats means an error would be printed
                printError(5);
            } else if (partName.length() != 0) {
                if (isInvValid(partMax, partMin, partStock)) {
                    if(inHouseRadio.isSelected()) { // if the in house radio button is selected
                        try {
                           IdMachineField = Integer.parseInt(swtichmachineidfield.getText()); // get machine id text
                           InHouse addInHousePart = new InHouse(partId, partName, partPrice, partStock, partMin, partMax, IdMachineField); // create new part and add to inventory
                           addInHousePart.setId(Inventory.createNewIdPart()); // set id using method from inventory class
                           Inventory.addPart(addInHousePart); // add part to inventory
                           successIfPartAdded = true; // if added indicate true. 
                        } catch (Exception e) { // if any issues print error. 
                            printError(2); // printed
                        }
                    }
                    if(outSourcedRadio.isSelected()) { // if outsourced toggle is selected
                        nameOfCompany = swtichmachineidfield.getText(); // get text for company from machine if field
                        Outsourced addOutsourcedPart = new Outsourced(partId, partName,partPrice, partStock, partMin, partMax, nameOfCompany); // create new outsourced part using constructor 
                        addOutsourcedPart.setId(Inventory.createNewIdPart()); // set if using method from inventory class
                        Inventory.addPart(addOutsourcedPart); // add part to inventory 
                        successIfPartAdded = true; // indicate true. 
                    }
                    if(successIfPartAdded = true) { // if true, we go to main menu
                      Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                      Scene s = new Scene(p);
                      Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      st.setScene(s);
                      st.show(); // set stage and scene to go to main  menu 
                    }
                }
            }        
        } catch(Exception e) { // if any issues print error.
            printError(1);
        }
    }
    
    /**
     * method used in other methods to print on to screen errors to alert a user. 
     * @param type of type int. 
     */
    private void printError(int type) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert e = new Alert(Alert.AlertType.ERROR);

        switch (type) {
            case 1 -> {
                a.setTitle("User Error!");
                a.setHeaderText("You have not added a part!");
                a.setContentText("There are missing fields or blank values or incorrect data type in field");
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
                e.setHeaderText("Entered Inventory value is invalid");
                e.setContentText("Inventory value must be = to or between min and max values.");
                e.showAndWait();
                break;
            }
            case 5 -> {
                e.setTitle("User Error!");
                e.setHeaderText("Entered part name is invalid");
                e.setContentText("Part name field can't be empty. Please add a part name.");
                e.showAndWait();
                break;
            }
        }
    }
    /**
     * checks if inv and minimum values are in valid range. iff they are not
     * then an error will be displayed. 
     * @param maxCheck
     * @param minCheck
     * @param stockCheck
     * @return boolean to indicate success
     */
    private boolean isInvValid(int maxCheck, int minCheck, int stockCheck) {
        boolean check = true; // set as true intially
        
        if (stockCheck < minCheck || stockCheck > maxCheck) { // check if it is valid
            check = false; // if not valid set to false
            printError(4); // pritn error
        }
        
        if (minCheck <= 0 || minCheck >= maxCheck) { // check if valid
            check = false; // if not valid set to false
            printError(3); // print error
        }
        
        return check; // return boolean to indicate success. 
    } 
    
    // private boolean isMinValid(int maxCheck, int minCheck) {
       // boolean check = true;
        // if (minCheck <= 0 || minCheck >= maxCheck) {
           // check = false;
            // printError(3);
        // }
        // return check;
    // }
}

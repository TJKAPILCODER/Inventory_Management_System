/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class for the add product menu
 * Contains all methods to control the add product menu 
 * @author tjkapil
 */
public class AddProductMenuController implements Initializable {
    /**
     * used to create alerts on screen 
     */
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        
    /**
     * table column for id
     */
    @FXML
    private TableColumn<Part, Integer> IdPartColumn;
    /**
     * table column for associate parts id
     */
    @FXML
    private TableColumn<Part, Integer> IdPartColumnAssociated;
    
    /**
     * text field for id
     */
    @FXML
    private TextField IdTextFieldProduct;
    /**
     * table column for inventory (stock)
     */
    @FXML
    private TableColumn<Part, Integer> InvPartColumn;
    /**
     * table column for associated parts Inventory (stock)
     */
    @FXML
    private TableColumn<Part, Integer> InvPartColumnAssociated;
    /**
     * text field for inventory (stock)
     */
    @FXML
    private TextField InvTextFieldProduct;
    /**
     * text field for max inventory
     */
    @FXML
    private TextField MaxTextFieldProduct;
    /**
     * text field for min inventory
     */
    @FXML
    private TextField MinTextFieldProduct;
    /**
     * table column for part name
     */
    @FXML
    private TableColumn<Part, String> NamePartColumn;
    /**
     *  table column for associated part 
     */
    @FXML
    private TableColumn<Part, String> NamePartColumnAssociated;
    
    /**
     * text field for name
     */
    @FXML
    private TextField NameTextFieldProduct;
    /**
     * table column for price 
     */
    @FXML
    private TableColumn<Part, Double> PricePartColumn;
    /**
     * table column for associated part price
     */
    @FXML
    private TableColumn<Part, Double> PricePartColumnAssociated;
    
    /**
     * text field for price of part
     */
    @FXML
    private TextField PriceTextFieldProduct;
    
    /**
     * TEXT FIELD FOR SEARCHING FOR PRODUCT IN TABLE VIEW 
     */
    @FXML
    private TextField SearchPartTextField;
    /**
     *  table view top for products
     */
    @FXML
    private TableView<Part> tableView;
    /**
     * table view bottom for associated parts of products
     */
    @FXML
    private TableView<Part> tableViewAssociated;
    /**
     * Array List for adding in associated parts of products 
     */
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    

    /**
     * Initializes the controller class and adds data to the table view so that it
     * can be shown to the user. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InvPartColumn.setCellValueFactory(new PropertyValueFactory<>("stock")); // set stock
        IdPartColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // set id
        NamePartColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // set name
        PricePartColumn.setCellValueFactory(new PropertyValueFactory<>("price")); // set price
        tableView.setItems(Inventory.getAllParts()); // set items to table view by getting from inventory. 
        
        InvPartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("stock")); // set stock
        IdPartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("id")); // set id
        NamePartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("name")); // set name
        PricePartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("price")); // set price
    }
    /**
     * when a user clicks on a part and adds it, it is added to the associated parts table,
     * but if no part is selected and user clicks add, then an error will pop up on screen. 
     * @param event of type ActionEvent
     */ 
    public void AddOnClick(ActionEvent event) {
        Part userSelectedPart = tableView.getSelectionModel().getSelectedItem(); // hold the part selected by the user
        if(userSelectedPart == null) { // if the user does not click a part and clicks button and error is displayed
            printError(5); // error printed on screen
        } else if (userSelectedPart != null) { // if user does click a part
            associatedPartsList.add(userSelectedPart); // then we add that part to an array list
            tableViewAssociated.setItems(associatedPartsList); // we add that arraylist to the table view
            
        }
    }

    /**
     * When the user clicks cancel, a confirmation alert is displayed
     * if user confirms that the program goes to main menu screen and ignores any changes made. 
     * @param event of type ActionEvent
     * @throws IOException 
     */
    public void CancelOnClick(ActionEvent event) throws IOException {
        a.setTitle("Alert"); // set title text
        a.setContentText("Ignore changes and head to main menu screen?"); /// set body text 
        Optional<ButtonType> r = a.showAndWait(); // wait for user response

        if (r.isPresent() && r.get() == ButtonType.OK) { // when user clicks button
            Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // set stage and scene and head to main menu screen
        }
    }
    /**
     * check if inventory and  minimum amounts are valid if not then error will be displayed
     * @param maxCheck of type int
     * @param minCheck of type int
     * @param stockCheck if type int
     * @return boolean to indicate if successful; 
     */
    private boolean isInvValid(int maxCheck, int minCheck, int stockCheck) {
        boolean check = true; // set t true intially 
        
        if (stockCheck < minCheck || stockCheck > maxCheck) { // check if stock within range
            check = false; // if it is not then it becomes false
            printError(4); // print error
        } 
        
        if (minCheck <= 0 || minCheck >= maxCheck) { // check if min in range
            check = false; // if it is not, becomes false
            printError(3); // print error
        }
        
        return check; // return boolean results 
    }
    
    // private boolean isMinValid(int maxCheck, int minCheck) {
    //    boolean check = true;
    //    if (minCheck <= 0 || minCheck >= maxCheck) {
    //        check = false;
    //       printError(3);
    //    }
    //    return check;
  //  }

    /**
     * this method will remove a part from associated parts table when clicked on by user and after
     * remove button is clicked. 
     * @param event of type ActionEvent
     */
    public void RemoveOnClick(ActionEvent event) {
        Part userSelectedPart = tableViewAssociated.getSelectionModel().getSelectedItem(); // hold part selected by user
        if(userSelectedPart == null) { // if the user does not click a part and clicks remove
            printError(5); // an error will be printed 
        } else if (userSelectedPart != null) { // if the associated part is clicked by user
            a.setTitle("User Alert!"); // set box title
            a.setContentText("Move forward with removing selected part?"); // set box body content
            Optional<ButtonType> r = a.showAndWait(); // wait for user response
            
            if (r.isPresent() && r.get() == ButtonType.OK) { // when user clicks button
                associatedPartsList.remove(userSelectedPart); // remove part selecred by user for associatedpartslist inventory arraylist
                tableViewAssociated.setItems(associatedPartsList); // set back the list with associated parts available. 
            }
        }
    }

    /**
     * method for the save button.
     * When user clicks save, the new part is added to the inventory and can be seen on the table view
     * after program goes to mains screen. If any issues, then an error is displayed on screen. 
     * @param event of type ActionEvent
     */
    public void SaveOnClick(ActionEvent event) {
        try {
            String productName = NameTextFieldProduct.getText();
            int productMin = Integer.parseInt(MinTextFieldProduct.getText());
            Double productPrice = Double.parseDouble(PriceTextFieldProduct.getText());
            int productStock = Integer.parseInt(InvTextFieldProduct.getText());
            int productMax = Integer.parseInt(MaxTextFieldProduct.getText());
            int productId = 0;
            
            if(productName.length() == 0) { // if the name of the product is not put in, 
                printError(6);// print an alert for user
            } else if (productName.length() != 0) { // if the user has put in a name
                if (isInvValid(productMax, productMin, productStock)) {
                    Product newProductToAdd = new Product(productId, productName, productPrice, productStock, productMin, productMax);
                    for(Part p : associatedPartsList) { // make an new part, and scan array list for that part
                        newProductToAdd.addAssociatedPart(p); // add part to array list
                    }
                    newProductToAdd.setId(Inventory.createNewIdProduct()); // get new id through inventory method
                    Inventory.addProduct(newProductToAdd); // add product to inventory list
                    Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                    Scene s = new Scene(p);
                    Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    st.setScene(s);
                    st.show(); // setup stage and scene and head to main menu 
                }
            }
        } catch (Exception e) {
            printError(1);
        }
    }
    
    /**
     * this method will search for a part based on its if or name, what ever is entered by the user
     * @param event  of type ActionEvent
     */
    public void SearchProductField(ActionEvent event) {
        ObservableList<Part> partsList = Inventory.getAllParts(); // get all parts and store them in arraylist
        ObservableList<Part> foundPart = FXCollections.observableArrayList(); // temp arraylist to store found part
        String userSearch = SearchPartTextField.getText(); // get text user entered
        
        for (Part p : partsList) { // if text matches anything in inventory
            if (String.valueOf(p.getId()).contains(userSearch) || p.getName().contains(userSearch)) {
                foundPart.add(p); // and that part to temp arraylist. 
            }
        }
        tableView.setItems(foundPart); // set the found part to the table view
        
        if(foundPart.isEmpty()) { // if no part found
            printError(1); // print error 
        }
    }
   /**
    * method to print errors to users, to be used in other methods
    * @param type int. 
    */ 
    private void printError(int type) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert e = new Alert(Alert.AlertType.ERROR);

        switch (type) {
            case 1 -> {
                a.setTitle("User Error!");
                a.setHeaderText("A product has not been added");
                a.setContentText("Please remove blank fields or missing values.");
                a.showAndWait();
                break;
            }
            case 2 -> {
                a.setTitle("User Information!");
                a.setHeaderText("This part is not found");
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
            case 5 -> {
                e.setTitle("User Error!");
                e.setHeaderText("You have not selected a part!");
                e.showAndWait();
                break;
            }
            case 6 -> {
                e.setTitle("User Error!");
                e.setHeaderText("part name entered is invalid");
                e.setContentText("Part name field is blank.");
                e.showAndWait();
                break;
            }
        }
    }
}

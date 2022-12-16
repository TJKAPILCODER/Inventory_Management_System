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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class for modifying a product
 * contains methods to modify a product. 
 * @author tjkapil
 */
public class ModifyProductMenuController implements Initializable {
    /**
     * used to create alerts for user 
     */
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    /**
     * column for part id
     */
    @FXML
    private TableColumn<Part, Integer> IdPartColumn;
    /**
     * column for associated pard id 
     */
    @FXML
    private TableColumn<Part, Integer> IdPartColumnAssociated;
    /**
     * cold for id text field
     */
    @FXML
    private TextField IdTextFieldProduct;
    /**
     * column for inventory 
     */
    @FXML
    private TableColumn<Part, Integer> InvPartColumn;
    /**
     * column for inventory for associated part
     */
    @FXML
    private TableColumn<Part, Integer> InvPartColumnAssociated;
    /**
     * text field for inventory product
     */
    @FXML
    private TextField InvTextFieldProduct;
    /**
     * text field for max inventory of product
     */
    @FXML
    private TextField MaxTextFieldProduct;
    /**
     * textfield for min inventory of product
     */
    @FXML
    private TextField MinTextFieldProduct;
    /**
     * column for name of part
     */
    @FXML
    private TableColumn<Part, String> NamePartColumn;
    /**
     * column for name of associated part
     */
    @FXML
    private TableColumn<Part, String> NamePartColumnAssociated;
    /**
     * text field for name of produict
     */
    @FXML
    private TextField NameTextFieldProduct;
    /**
     * column for price of product 
     */
    @FXML
    private TableColumn<Part, Double> PricePartColumn;
    /**
     * column for price of associated part 
     */
    @FXML
    private TableColumn<Part, Double> PricePartColumnAssociated;
    
    /**
     * text field for price of product
     */
    @FXML
    private TextField PriceTextFieldProduct;
    /**
     * text field for searching for product
     */
    @FXML
    private TextField SearchPartTextField;
    /**
     * TABLE VIEW FOR parts
     */
    @FXML
    private TableView<Part> tableView;
    /**
     * table view for associated parts 
     */
    @FXML
    private TableView<Part> tableViewAssociated;
    /**
     * array list for associated parts
     */
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    /**
     * part selected by user to be modified. 
     */
    Product ProductSelectedByUser; 


    /**
     * Initializes the controller class and adds data from main menu to text field when
     * modifying a part
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProductSelectedByUser = MainMenuController.getUserSelectedProduct(); // get part selected by user to modify
        associatedPartsList = ProductSelectedByUser.getAllAssociatedParts(); // get all associated parts of part selected by user
        
        PricePartColumn.setCellValueFactory(new PropertyValueFactory<>("price")); // set price
        IdPartColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // set id
        NamePartColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // set name
        InvPartColumn.setCellValueFactory(new PropertyValueFactory<>("stock")); // set stock
        tableView.setItems(Inventory.getAllParts()); // set all items to table view
        
        PricePartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("price")); // set price
        IdPartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("id"));  // set id
        NamePartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("name")); // set name
        InvPartColumnAssociated.setCellValueFactory(new PropertyValueFactory<>("stock")); // set stock
        tableViewAssociated.setItems(associatedPartsList); // set all items to associated parts table view
        
        MaxTextFieldProduct.setText(String.valueOf(ProductSelectedByUser.getMax())); // get max inv of user selected product
        MinTextFieldProduct.setText(String.valueOf(ProductSelectedByUser.getMin())); // get min inv of usier selected product
        IdTextFieldProduct.setText(String.valueOf(ProductSelectedByUser.getId())); // get id of user selected product
        NameTextFieldProduct.setText(ProductSelectedByUser.getName()); // get name of user selected product
        InvTextFieldProduct.setText(String.valueOf(ProductSelectedByUser.getStock())); // get stock of user selected product
        PriceTextFieldProduct.setText(String.valueOf(ProductSelectedByUser.getPrice())); // get price of user selected product
              
    }    
    
    /**
     * method to add product user clicked to associated parts table
     * @param event of type ActionEvent
     */
    public void AddOnClick(ActionEvent event) {
        Part userSelectedPart = tableView.getSelectionModel().getSelectedItem(); // hold part selected by user
        if(userSelectedPart == null) { // uf user does not click part and hits add
            printError(5); // error will be printed
        } else if (userSelectedPart != null) { // if user does click a part
            associatedPartsList.add(userSelectedPart); // add part to array list
            tableViewAssociated.setItems(associatedPartsList); // add arraylist to table view
            
        }
    }

    /**
     * method used to confirm with the user if they want to go to mains screen
     * and ignore any changes made to modifying a part. 
     * @param event of type ActionEvent
     * @throws IOException 
     */
    public void CancelOnClick(ActionEvent event) throws IOException {
        a.setTitle("Alert"); // set title of box
        a.setContentText("Ignore changes and head to main menu screen?"); // set body content of box
        Optional<ButtonType> r = a.showAndWait(); // wait for user response

        if (r.isPresent() && r.get() == ButtonType.OK) { // when user responds
            Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // set up stage and scene and head to mains screen
        }
    }

    /**
     * method used to remove a part from the associated part table. 
     * if the user does not selected an associated part and tries to remove
     * an error will be displayed. 
     * @param event 
     */
    public void RemoveOnClick(ActionEvent event) {
        Part userSelectedPart = tableViewAssociated.getSelectionModel().getSelectedItem(); // hold part selected by user
        if(userSelectedPart == null) { // if user does not select a part and hits remove
            printError(5); // display an error
        } else { 
            a.setTitle("User Alert!"); // set box title
            a.setContentText("Move forward with removing selected part?"); // set box body content 
            Optional<ButtonType> r = a.showAndWait(); // wait for user response
            
            if (r.isPresent() && r.get() == ButtonType.OK) { // if user clicks a button 
                associatedPartsList.remove(userSelectedPart); // remove that part from list
                tableViewAssociated.setItems(associatedPartsList); // set list again with part removed into table view
            }
        }
    }
    /**
     * checks if inventory and minimum value are in valid range, if they are not
     * then an error will be displayed. 
     * @param maxCheck
     * @param minCheck
     * @param stockCheck
     * @return boolean to indicate success. 
     */
    private boolean isInvValid(int maxCheck, int minCheck, int stockCheck) {
        boolean check = true; // set to true intially 
        if (stockCheck < minCheck || stockCheck > maxCheck) {  // check if valid
            check = false; // if not valid 
            printError(4); // print error
        }
        
        if (minCheck <= 0 || minCheck >= maxCheck) { // check if valid
            check = false; // if not valid
            printError(3); // set to 
        }
        
        return check; // return boolean to indicate success. 
    }
    
    // private boolean isMinValid(int maxCheck, int minCheck) {
    //    boolean check = true;
    //    if (minCheck <= 0 || minCheck >= maxCheck) {
    //        check = false;
    //        printError(3);
    //    }
    //    return check;
  //  }

    /**
     * method to save what the user has modified and put int onto the main screen table view.
     * IF any issues are present then an user alert will be displayed. 
     * @param event of type ActionEvent
     */
    public void SaveOnClick(ActionEvent event) {
        try {
            int productMin = Integer.parseInt(MinTextFieldProduct.getText()); // get inv  minimum from textfield
            int productMax = Integer.parseInt(MaxTextFieldProduct.getText()); // get inv maximum from textfield
            int productId = ProductSelectedByUser.getId(); // get id from text field
            String productName = NameTextFieldProduct.getText(); // get name from textfield
            Double productPrice = Double.parseDouble(PriceTextFieldProduct.getText()); // get price from textfield
            int productStock = Integer.parseInt(InvTextFieldProduct.getText()); // get inventory stock from text field
            
            if(productName.length() == 0) { // if the product name is not entered
                printError(6); // print error
            } else if(productName.length() != 0) { // if product name is entered
                if (isInvValid(productMax, productMin, productStock)) {
                    Product newProductToAdd = new Product(productId, productName, productPrice, productStock, productMin, productMax);
                    for(Part p : associatedPartsList) { // check if it is within range
                        newProductToAdd.addAssociatedPart(p); // add product to arraylist
                    }
                    Inventory.addProduct(newProductToAdd); // add to inventory
                    Inventory.deleteProduct(ProductSelectedByUser); // delete original from inventory
                    Parent p = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
                    Scene s = new Scene(p);
                    Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    st.setScene(s);
                    st.show(); // set stage and scene and head to main screen
                }
            }
        } catch (Exception e) { // if any issies
            printError(1); // print error. 
        }
    }
    
    /**
     * method to search for a part by id or name, 
     * if any issues then print errors. 
     * @param event of Type ActionEvent
     */
    public void SearchPartField(ActionEvent event) {
        ObservableList<Part> partsList = Inventory.getAllParts(); // get all parts and store in array list
        ObservableList<Part> foundPart = FXCollections.observableArrayList(); // make temp array list for part 
        String userSearch = SearchPartTextField.getText(); // get id or name that user searched
        
        for (Part p : partsList) { // if id or name is found
            if (String.valueOf(p.getId()).contains(userSearch) || p.getName().contains(userSearch)) {
                foundPart.add(p); // then add id or name to temp array list
            }
        }
        tableView.setItems(foundPart); // set temp array list to table view so that it can be seen by user. 
         
        if(foundPart.isEmpty()) { // id the list is empty that means part not found
            printError(1); // print user alert. 
        }
    }
    /**
     * method to use in other methods. 
     * This method contains error to be printed when there are issues. 
     * error will be printed in dialog box. 
     * @param type of type Int
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
                a.setContentText("Enter valid part");
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
    


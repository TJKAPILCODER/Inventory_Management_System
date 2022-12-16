/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;

/**
 * FXML Controller class
 * This class controls the main menu page, while contains other functions such as
 * adding, modifying part and adding, modifying product. It also contains an exit button.
 * 
 * @author tjkapil
 */
public class MainMenuController implements Initializable {
    
    /**
     * Alert a and e to set up alerts when needed. 
     */
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    
    Alert e = new Alert(Alert.AlertType.ERROR);
    
    /**
     * The part that is selected by the user to be modified
     */
    private static Part partSelectedToModify;
    
    /**
     * the product that is selected by the user to be modified 
     */
    private static Product productSelectedToModify;
    
    /**
     * This is the table view column for the part's ID attribute. 
     */
    @FXML
    private TableColumn<Part, Integer> PartIdColumn;
    
    /**
     * This is the table view column for the part's Inventory attribute. 
     */
    @FXML
    private TableColumn<Part, Integer> PartInvColumn;
    
    /**
     * This is the table view column for the part's Name attribute. 
     */
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    
    /**
     * This is the table view column for the part's Price attribute. 
     */
    @FXML
    private TableColumn<Part, Double> PartPriceColumn;
    
    /**
     * This is the table view column for the products ID attribute. 
     */
    @FXML
    private TableColumn<Product, Integer> ProductIdCol;
    
    /**
     * This is the table view column for the products Inventory attribute. 
     */
    @FXML
    private TableColumn<Product, Integer> ProductInvCol;
    
    /**
     * This is the table view column for the products Name attribute. 
     */
    @FXML
    private TableColumn<Product, String> ProductNameCol;
    
    /**
     * This is the table view column for the products Price attribute. 
     */
    @FXML
    private TableColumn<Product, Double> ProductPriceCol;
    
    /**
     * This is the table view for the parts table. 
     */
    @FXML
    private TableView<Part> partsTable;
    
    /**
     * This is the table view for the products table. 
     */
    @FXML
    private TableView<Product> productsTable;
    
    /**
     * This is the text field for searching for a part. 
     */
    @FXML
    private TextField searchPartField;
    
    /**
     * This is the text field for searching for a product. 
     */
    @FXML
    private TextField searchProductField;


    /**
     * Initializes the controller class for main menu and added the following
     * data to the table-view for parts.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //This will use methods to add data to both part and product table views. 
        productsTable.setItems(Inventory.getAllProducts()); // we get items from array lists
        // and set them to the parts table. 
        partsTable.setItems(Inventory.getAllParts()); // we get items from array lists
        // and set them to the products table. 
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price")); // sets the price column
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // sets the name column 
        ProductInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));  // sets the stock column 
        ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id")); // sets the id column
        
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        
    }
    
    /**
     * method to get product selected by the user
     * @return product that is selected of type Product. 
     */
    public static Product getUserSelectedProduct() {
        return productSelectedToModify;
    }
    
    /**
     * method to get part selected by the user
     * @return part that is selected selected of type Part
     */
    public static Part getUserSelectedPart() {
        return partSelectedToModify;
    }
    
    
    /**
     * When the add button is clicked for Part Table view, it takes you to add part screen.
     * The add part screen is launched so user can put in details to add a part. 
     * @param event 
     * @throws java.io.IOException 
     */   
    public void ToAddPart(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show(); // This will set up the Scene and that show that scene to the user on screen. 
    }

    /**
     * When the add button is clicked for Products Table view, it takes you to add Product screen.
     * The add Product screen is launched so user can put in details to add a Product. 
     * @param event 
     * @throws java.io.IOException 
     */ 
    public void ToAddProduct(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        Scene s = new Scene(p);
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(s);
        st.show(); // This will set up the Scene and that show that scene to the user on screen. 
    }

    /**
     * When the part delete button is clicked, a confirmation will be asked
     * to make sure user really wants to delete part. It will then remove the part
     * from the part array list and reprint the data to the table view without the deleted
     * part. 
     * @param event 
     */
    public void ToDeletePart(ActionEvent event) {
        Part UserSelectedPart = partsTable.getSelectionModel().getSelectedItem(); // get part that
        // is selected by user and store that part in a variable. This variable will be off type part itself. 
        if(UserSelectedPart == null) { // if the user does not click a part and hits delete
            // an error will be printed on to the screen, to ask the user to click a part. 
            printError(3); // the error will be printed on screen itilizing the print error function. 
        } else if (UserSelectedPart != null) { // if the user does actully click a part. 
            a.setTitle("User Alert!"); // this will set the title of the box. 
            a.setContentText("Do you want to delete the selected part?"); // wprding inside box
            Optional<ButtonType> r = a.showAndWait(); // wait for user respose

            if (r.isPresent() && r.get() == ButtonType.OK) { // after user responds
                Inventory.deletePart(UserSelectedPart); // deleted part selected by the user. 
            }
        }    
    }
    /**
     * This method contains all of the errors and alerts that will be shown to the user if needed.
     * this method will be used inside other methods. 
     * @param type of int. 
     */
    private void printError(int type) {

        switch (type) {
            case 1 -> {
                a.setTitle("User Information!");
                a.setHeaderText("Part Not available! Please enter a valid part.");
                a.showAndWait();
                break;
            }
            case 2 -> {
                a.setTitle("User Information!");
                a.setHeaderText("Product Not available! Please enter a valid product.");
                a.showAndWait();
                break;
            }
            case 3 -> {
                e.setTitle("User Error!");
                e.setHeaderText("Part is not selected");
                e.showAndWait();
                break;
            }
            case 4 -> {
                e.setTitle("User Error!");
                e.setHeaderText("Product is not selected");
                e.showAndWait();
                break;
            }
            case 5 -> {
                e.setTitle("User Error!");
                e.setHeaderText("There Are Parts Associated With This Product");
                e.setContentText("Remove all parts from product before deleting.");
                e.showAndWait();
                break;
            }
        }
    }

   /**
    * method to delete a product when it is clicked by the user. Will confirm with the user
    * if the user really wants to delete the product. 
    * @param event of type ActionEvent
    */
    public void ToDeleteProduct(ActionEvent event) {
        int size = 1;
        Product UserSelectedProduct = productsTable.getSelectionModel().getSelectedItem(); // hold product clicked by user
        if(UserSelectedProduct == null) { // if no product selected and delete button clicked
            printError(4); // then print error 4
        } else if (UserSelectedProduct != null) { // if delete button is clicked. 
            a.setTitle("User Alert!"); // open confirmation box 
            a.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> r = a.showAndWait(); // wait for user response
            if (r.isPresent() && r.get() == ButtonType.OK) { // when user clicks button 
                ObservableList<Part> associatedParts = UserSelectedProduct.getAllAssociatedParts(); // hold all associated parts in this arraylist
                if(associatedParts.size() >= size) { // if size is greater than 1, then we can't delete the product and a error will be printed. 
                    printError(5); // error will be printed
                } else { // if size is not grater than equal to 1 then its zero
                    Inventory.deleteProduct(UserSelectedProduct); // the product can be deleted with no issues. 
                }
            }
        }
    }

    /**
     * When modify part button is clicked then the user is taken to new screen where
     * they can modify a part that is clicked. 
     * @param event type ActionEvent
     * @throws IOException 
     */
    public void ToModifyPart(ActionEvent event) throws IOException {
        partSelectedToModify = partsTable.getSelectionModel().getSelectedItem(); // hold part that is clicked by user to modify
        if(partSelectedToModify == null) { // if the user clicks modify without clicking buttom
            printError(3); // then an error is printed. 
        } else if (partSelectedToModify != null) { // if the user does click a product. 
            Parent p = FXMLLoader.load(getClass().getResource("/view/ModifyPartMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // used to launch the modify part screen by setting stage and scene. 
        }
    }

    /**
     * When modify product button is clicked then the user is taken to new screen where
     * they can modify a product that is clicked. 
     * @param event type ActionEvent
     * @throws IOException 
     */
    public void ToModifyProduct(ActionEvent event) throws IOException {
      productSelectedToModify = productsTable.getSelectionModel().getSelectedItem(); // hold product seleced by user
      if(productSelectedToModify == null) {
          printError(4);
      } else if (productSelectedToModify != null) {
            Parent p = FXMLLoader.load(getClass().getResource("/view/ModifyProductMenu.fxml"));
            Scene s = new Scene(p);
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.setScene(s);
            st.show(); // stage and scene are set to go to the modify product scfeen
        }
    }

    /**
     * This method is used when the user searches for a part.
     * It will print part to screen if it is available based on part name or part id. 
     * @param event 
     */
    public void resultsSearchPart(ActionEvent event) {
        ObservableList<Part> foundPart = FXCollections.observableArrayList(); // if the part is found we can store it in this arraylist
        ObservableList<Part> partsList = Inventory.getAllParts();  // here we get all the parts and store them in the array list. 
        String userSearch = searchPartField.getText(); // we get the text from the user that they typed in the search field. 
        
        for(Part p:partsList) { // we search the arraylist with all parts. 
            if(String.valueOf(p.getId()).contains(userSearch) || p.getName().contains(userSearch)) {
                // if any of the parts match by name or id
                foundPart.add(p); // we add them to the temp arraylist. 
            }
        }
        partsTable.setItems(foundPart); // we then set the parts table to the part found
        if(foundPart.isEmpty()) { // if partfound is empty, then we did not find a matching part
            printError(1); // we then print an error indicating this. 
        }
    }

    /**
     * This method is used when the user searches for a product.
     * It will print product to screen if it is available based on product name or product id. 
     * @param event
     */
    public void resultsSearchProducts(ActionEvent event) {
        ObservableList<Product> foundProduct = FXCollections.observableArrayList(); // if the Product is found we can store it in this arraylist
        ObservableList<Product> productsList = Inventory.getAllProducts(); // here we get all the Products and store them in the array list. 
        String userSearch = searchProductField.getText(); // we get the text from the user that they typed in the Product search field. 
        
        for(Product p:productsList) { // we search the arraylist with all Products. 
            if(String.valueOf(p.getId()).contains(userSearch) || p.getName().contains(userSearch)) {
                // if any of the Products match by name or id
                foundProduct.add(p); // we add them to the temp arraylist. 
            }
        }
        productsTable.setItems(foundProduct); // we then set the Products table to the Product found
        if(foundProduct.isEmpty()) { // if Productfound is empty, then we did not find a matching Product
            printError(2); // we then print an error indicating this. 
        }
    }
    /**
     * method to exit program after asking for confirmation. We will exit to outside the program
     * @param event of type ActionEvent
     */
    public void toExitProgram(ActionEvent event) {
        a.setContentText("Are you sure you want to exit program?"); // set dialog box content text
        Optional<ButtonType> r = a.showAndWait(); // wait for user response
        
        if(r.isPresent() && r.get() == ButtonType.OK) { // when user reponds with button click
            Platform.exit(); // exit the program to outside the program. 
        }
    }
}

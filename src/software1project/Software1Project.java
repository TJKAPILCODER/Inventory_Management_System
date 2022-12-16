/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package software1project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * 
 * 
 * 
 * The "JAVADOC" files are contained within the Software1Project folder.
 * Within the Software1Project folder, there is a folder called "javadoc", within the 
 * javadoc folder is a file called index.html, where javadoc comment geenrated by the 
 * NetBeans IDE can be found. 
 * 
 * "FUTURE ENHANCEMENT": Currently the Inventory Management System programs has
 * no database attached to it such as MYSQL or POSTGRE SQL. A database could be added to this
 * program which would let us save data input into the application into a outside database. This data
 * can then be used and have other uses, it can also be analyzed by using functions inside of 
 * database management system programs. 
 * 
 * "RUNTIME ERROR": I was initially getting a RUNTIME error when I was trying to generate JAVADOCs
 * for the project. Due to this error I was not able to generate the javadoc for the files. 
 * The error was stating that an identifier was expected. I fixed this error by looking for
 * the function with a missing identifier in the javadoc description and adding it in. 
 * 
 * Main file for inventory management system. This system has functions such as adding, modifying parts and 
 * adding/modifying products. You can also exit the program. Parts can contain associated
 * parts which is a separate inventory. 
 * 
 * @author tjkapil : 12/14/2022 : C482 SOFTWARE I : Inventory Management System
 */
public class Software1Project extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    /**
     * sets up stage and shows scene
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        /**
         * set up stage and scene for main menu
         * set main title of program box 
         */
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System"); // set main title of box 
        stage.show(); // set up stage and scene for main menu 
        
        /**
         * // add in sample data to be used in table views for part
         */
        Part p = new InHouse(7, "Test Part1", 7.47, 22, 20, 31, 13);
        Inventory.addPart(p);
        Part p2 = new InHouse(4, "Test Part2", 7.48, 23, 21, 32, 14);
        Inventory.addPart(p2);
        Part p3 = new InHouse(5, "Test Part3", 7.48, 24, 22, 33, 15);
        Inventory.addPart(p3);
        /**
         * // add in sample data to be used in table views for product
         */
        Product p4 = new Product(8, "Test Product1", 9.35, 20, 30, 13);
        Inventory.addProduct(p4);
        Product p5 = new Product(4, "Test Product2", 9.36, 21, 31, 14);
        Inventory.addProduct(p5);
        Product p6 = new Product(5, "Test Product3", 9.37, 22, 32, 15);
        Inventory.addProduct(p6); 
    }
    
}

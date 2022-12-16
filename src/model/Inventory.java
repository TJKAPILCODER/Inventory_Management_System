/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * class for methods -> parts/products
 * this class will create an inventory that will contain the parts in an Observable
 * Array List Data type. 
 * @author tjkapil
 */
public class Inventory {
    
    private static int incrementPartId = 10; // int part id to be created will start at 10
    private static int incrementProducttId = 10; // int part id to be created will start at 10
    private static Product productSelectTable = null; // not being used
    private static Part partSelectTable = null; // not being used 
    private static ObservableList<Part> allParts = FXCollections.observableArrayList(); // list containing all parts 
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList(); // list containing all products. 
    
    /**
     * return array list with all parts that are in the inventory. 
     * @return return array list observable list with all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**
     * return array list with all products that are in the inventory. 
     * @return return array list observable list with all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    /**
     * To save new part made by user to inventory. 
     * @param newPart that is made by user Added to allParts inventory. 
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    /**
     * To save new product made by user to inventory. 
     * @param newProduct to be added to allProducts arrayList for inventory. 
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    /**
     * when user search for parts by id, if that part is found it is returned. 
     * @param partId is entered to lookup part
     * @return datatype Part. 
     */
    public static Part lookupPart(int partId) {
        Part storeTempPart = null; // intially null because part not found by id
        ObservableList<Part> storeTempAllParts = Inventory.getAllParts(); // get all parts and store it in a new arraylist
        for(Part p : storeTempAllParts) { // move through list
            if(p.getId() == partId) { // if our part if matches with an id from the array list
                storeTempPart = p; // we set out variable equal to that part
            }
        }
        return storeTempPart; // we can than return that part because we found it. 
    }
    
    /**
     * when user search for products by id, if that product is found it is returned. 
     * @param productId, entered to look up product
     * @return datatype product
     */
    public static Product lookupProduct(int productId) {
        Product storeTempProduct = null; // intially null because part not found by id
        ObservableList<Product> storeTempAllProduct = Inventory.getAllProducts(); // get all products and store it in a new arraylist
        for(Product p : storeTempAllProduct) { // move through list
            if(p.getId() == productId) { // if our product if matches with an id from the array list
                storeTempProduct = p; // we set out variable equal to that product
            }
        }
        return storeTempProduct; // we can than return that product because we found it. 
    }
    /**
     * looks for user typed part name and returns name if it matches anything from part inventory. 
     * @param partName String
     * @return the part
     * This is an example of method overloading. 
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> tempPartList = FXCollections.observableArrayList(); // temp arraylist to store part
        ObservableList<Part> tempAllPartsList = Inventory.getAllParts(); // arraylist to store all parts
        for(Part p : tempAllPartsList) { // move through arraylist with all parts
            if(p.getName().contains(partName)) { // if partName matches are name in arraylist
                tempPartList.add(p); // we add that name to temp arraylist. 
            }
        }
        return tempPartList; // we can then return the part we just found
    }
    
    /**
     * looks for user typed product name and returns name if it matches anything from product inventory. 
     * @param productName tString
     * @return the product
     * This is an example of method overloading. 
     */
    public static ObservableList<Product> lookupProduct(String productName) { // temp arraylist to store product
        ObservableList<Product> tempProductList = FXCollections.observableArrayList(); // arraylist to store all products
        ObservableList<Product> tempAllProductList = Inventory.getAllProducts();
        for(Product p : tempAllProductList) { // move through arraylist with all products
            if(p.getName().contains(productName)) { // if productName matches are name in arraylist
                tempProductList.add(p); // we add that product name to temp arraylist.
            }
        }
        return tempProductList;  // we can then return the product we just found
    }
    /**
     * when user saves a modified part to the inventory, that part is updated in the table-view
     * @param index, where part is located in inventory
     * @param selectedPart part we want modified
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart); // in the inventory all parts
        // we set the part in that particular index to a new part.
    }
    /**
     * when user saves a modified product to the inventory it is updated in the product table-view
     * @param index where product is located in inventory. 
     * @param selectedProduct product we want modified
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct); // in the inventory all products
        // we set that product in that particular index to a new product.
    }
    /**
     * remove selected part from inventory, so it is not shown in table-view
     * @param selectedPart // part we want removed. 
     * @return boolean indicating if part was removed or not. 
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart); // from the all parts array risk we 
        // remove the part that is selected by user. 
    }
    /**
     * remove selected product from inventory, so it is not shown in table-view of products
     * @param selectedProduct / /product we want removed
     * @return boolean indicating if product was removed or not.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct); // from the all products array list we 
        // remove the part that is selected by user. 
    }  
    /**
     * this method is used to create a new part id. It builds on top of previous id number and
     * increments. 
     * @return int partId
     */
    public static int createNewIdPart() {
        return ++incrementPartId; // incremented id returned
    }
    /**
     * this method is used to create a new product id. It builds on top of previous id number and
     * increments it. 
     * @return product id
     */
    public static int createNewIdProduct() {
        return ++incrementProducttId; // incremented id returned
    }
}

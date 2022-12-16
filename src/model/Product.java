/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class, creates products for inventory. The products of this inventory have
 * associated parts. 
 * @author tjkapil
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList(); //Creates an arrayList where we can stores Array associated Parts
    private int id; // product integer id.
    private String name; // products String name.
    private double price; // products double price/
    private int stock; // products integer inventory.
    private int min; // products min amount integer.
    private int max; // products max amount integer. 
    
/**
 * Constructor for products 
 * @param id products
 * @param name products
 * @param price products
 * @param stock products
 * @param min minimum products
 * @param max maximum products
 */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    
    /**
     * method to get id of product
     * @return id of product
     */
    public int getId() {
        return id;
    }
    
    /**
     * method to set id of product
     * @param id of product
     */
    public void setId(int id) {
        this.id = id; // initialize 
    }
    
    /**
     * method to get name of product
     * @return String name of product 
     */
    public String getName() {
        return name;
    }
    /**
     * method to set name of product
     * @param name of product
     */
    public void setName(String name) {
        this.name = name; // initialize 
    }
    /**
     * method to get price of product 
     * @return double price of product 
     */
    public double getPrice() {
        return price;
    }
    /**
     * method to set price of product
     * @param price double of product 
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * method to get stock of product
     * @return int stock of product 
     */
    public int getStock() {
        return stock;
    }
    /**
     * method to set stock of product 
     * @param stock of product 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /** method to get minimum of product
     * @return min int of product 
     */
    public int getMin() {
        return min;
    }
    /**
     * method to set minimum of product
     * @param min of product 
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * method to get max of product
     * @return int max of product 
     */
    public int getMax() {
        return max;
    }
    
    /**
     * method to set maximum of product
     * @param max of product 
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * This method will add a part to the associated part list when
     * needed by the user. 
     * @param part of type Part 
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part); // add part to arraylist 
    }
    /**
     * This method deletes a part from the associated array list that was previously added. 
     * @param selectedAssociatedPart
     * @return boolean indicating if part was removed or not
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart); // delete part from array list 
        
    }
    /**
     * return whole array list of associated parts. It will populate the table of parts. 
     * @return List of associated Parts 
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
    
    
    
}
    
    
    


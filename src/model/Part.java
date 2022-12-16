/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Part class contains methods to get part information and create a part. 
 * @author tjkapil
 */
public abstract class Part { // abstract class, can't make objects from this, only inherit
    private int id; // part id int stored
    private String name; // part name string stored
    private double price; // part price double stored
    private int stock; // part inventory integer stored
    private int min; // part minimum value int stored
    private int max; // part maximum value int stored
    /**
     * Constructor for Part inventory to initialize variables 
     * @param id for part
     * @param name for part
     * @param price for part
     * @param stock for part
     * @param min for part
     * @param max  for part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * method to return the id of a part
     * @return id part
     */
    public int getId() {
        return id;
    }
    /**
     * method to set ID of a part
     * @param id part 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * method to get name of part 
     * @return String  name
     */
    public String getName() {
        return name;
    }
    /**
     * method to set name of part 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * method to return price of part
     * @return double price
     */
    public double getPrice() {
        return price;
    }
    /**
     * parameter to set price of a part
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * method to get stock inventory of a part
     * @return int stock 
     */
    public int getStock() {
        return stock;
    }
    /**
     * method to set stock inventory for a part
     * @param stock integer. 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * method to get minimum a amount of stock
     * @return min int
     */
    public int getMin() {
        return min;
    }
    /**
     * method to set minimum amount of stock 
     * @param min int
     */
    public void setMin(int min) {
        this.min = min;
    }
    /**
     * method to get max amount of stock 
     * @return 
     */
    public int getMax() {
        return max;
    }
    /**
     * method to set max amount of stock
     * @param max int
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    
}

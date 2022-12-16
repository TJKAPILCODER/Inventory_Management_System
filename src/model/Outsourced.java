/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * extends part abstract class. contains methods to be used with outsourced parts. 
 * can use methods from Part class since this is a base child class of parent class Part.
 * @author tjkapil
 */
public class Outsourced extends Part { // base class inheriates from parent class. 
    private String companyName; // variable that holds companyName from toggle switch
    
    /**
     * constructor for out-sourced parts
     * @param id for outsourced part
     * @param name for outsourced part
     * @param price for outsourced part
     * @param stock for outsourced part
     * @param min for outsourced part
     * @param max for outsourced part
     * @param companyName for outsourced part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max); // use parent class constructor
        this.companyName = companyName; // intialize companyName String
    }
     
    /**
     * set company name of outsourced part 
     * @param companyName to be set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }  
    
    /**
     * get company name of outsourced part
     * @return String company name
     */
    public String getCompanyName() {
        return companyName;
    }
}

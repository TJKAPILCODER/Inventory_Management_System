/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * extends part abstract class. Contains  methods for in house created 
 * parts such as getter and setter. 
 * Contains constructor to create an
 * object. 
 * // In-house is the base child class of the parent class called Part. 
 * // with In-house class you can use methods from the Part class as well as additional methods given below.
 * // variable called machine Id that will hold the machine id text field response. 
 * @author tjkapil
 */
public class InHouse extends Part { // In-house is the base child class of the parent class called Part. 
   // with In-house class you can use methods from the Part class as well as additional methods given below. 
    private int machineId; // variable called machine Id that will hold the machine id text field response. 
    /**
     * constructor for parts created In-house
     * @param id will take in an integer part id
     * @param name will take in a string part name
     * @param price will take in a double part price
     * @param stock will take in a integer inventory number for parts
     * @param min  will take in min integer inventory number for parts
     * @param max will take in max integer inventory number for parts
     * @param machineId will take in the integer machineId number. 
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) { // constuctor to create InHouse object. 
        super(id, name, price, stock, min, max); // constructor from the parent part class. 
        this.machineId = machineId; // initializing the new machine id variable exclusive to InHouse class. 
    } 
    
    /**
     * method to set machine id of in house part 
     * @param machineId which is an integer. 
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    /**
     * method to get Machine id of in house part 
     * @return machine id of part that is an integer. 
     */
    public int getMachineId() {
        return machineId;
    }   
}

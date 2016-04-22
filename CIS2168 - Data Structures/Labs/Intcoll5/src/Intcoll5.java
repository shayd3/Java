//*********************************************************************
// FILE NAME    : Intcoll5.java
// DESCRIPTION  : This file contains the class Intcoll5.
//*********************************************************************
import java.util.*;

public class Intcoll5 {

    private LinkedList<Integer> c;
    private int howmany;

    /**
     * Creates a new empty collection of integers
     */
    public Intcoll5() {
        c = new LinkedList<Integer>(); //Ensures c is a new empty list
        howmany = 0;

    }

    
    /**
     * Creates a new empty collection of integers
     *
     * @param i positive integer
     */
    public Intcoll5(int i) {
        c = new LinkedList<Integer>();
        howmany = 0;
    }

    
    /**
     * Pre-condition: Take in an object of Intcoll
     * Post-condition: Takes in an object, if parameter is not empty,
     * 		copy howmany and all information from object that is 
     * 		calling method to the parameter
     * 
     * @param obj
     */
	public void copy(Intcoll5 obj) {
		if((this != obj) && obj != null){
			c = new LinkedList<Integer>(); //Makes sure list is empty
			ListIterator<Integer> iterator = obj.c.listIterator();
			howmany = obj.get_howmany();
			while (iterator.hasNext()){
				c.add(iterator.next());
			}
		}
    }
	

    /**
     * Pre-condition: takes in integer
     * Post-condition: Checks to see if int i is in the collection
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {
    	return ((i > 0) && c.contains(i));
    }

    
    /**
     * Take in int i, if greater than 0 and does NOT exist in the collection,
     *  insert i into the collection and increment howmany
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
    	if(i > 0){
    		Integer I = new Integer(i); //Creates a new object of type Integer
    		if(!c.contains(I)){
    			c.add(I);
    			howmany++;
        	}
        }	
    }
    

    /**
     * Take in int i, if i exists in the collection, delete it. decrement howmany
     *
     * @param i positive integer
     */
    public void omit(int i) {
    	Integer I = new Integer(i);
    	if ((i > 0) && c.remove(I)){
    		howmany--;
    	}
    }
    

    /**
     * Returns number of integers in collection
     *
     * @return howmany
     */
    public int get_howmany() {
        return howmany;
    }
    

    /**
     * Prints out the entire collection
     */
    public void print() {
    	if(howmany > 0){
    		ListIterator<Integer> iterator = c.listIterator();
    		while(iterator.hasNext()){
    			System.out.println(iterator.next().intValue());
    		}
    	}
    }
    

    /**
     * Pre-condition: Takes in an object of Intcoll
     * Post-Condition: If both objects contain the same amount of integers
     * in the collection, compare integers inside of collection. If everything
     * is the same, return true. Otherwise false.
     *
     * @param obj object containing an array
     * @return result
     */
    public boolean equals(Intcoll5 obj) {
    	boolean result = obj.c.size() == c.size();
    	ListIterator<Integer> iterator = c.listIterator();
    	while(result && iterator.hasNext()){
    		result = obj.c.contains(iterator.next());
    	}
        return result;
    }
}

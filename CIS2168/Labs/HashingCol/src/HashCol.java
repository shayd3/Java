
import java.util.*;

public class HashCol {
	private HashMap c;
	private int key;
	private int value;
    private int howmany = 0;
    
    /**
     * Creates a new empty collection of integers
     * @return 
     */
    public HashCol(){
    	c = new HashMap();
    	this.howmany = 0;
    }
    
    public HashCol(int size) {
    	c = new HashMap(size);
        this.howmany = 0;
    }
    
    private int hash(int i){
    	return i % c.size();
    }

    /**
     * Pre-condition: Take in an object of Intcoll
     * Post-condition: Takes in an object, if parameter is not empty,
     * 		copy howmany and all information from object that is 
     * 		calling method to the parameter
     *
     * @param obj
     */

//    public void copy(HashCol obj) {
//        if (this != obj) {
//            c = new int[obj.c.length];
//            howmany = obj.get_howmany();
//            int j = 0;
//            while (j < this.get_howmany()) {
//                c[j] = obj.c[j];
//                j++;
//            }
//        }
//    }

    /**
     * Pre-condition: takes in integer
     * Post-condition: Checks to see if int i is in the collection
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {    	
    	if(c.containsValue(i) == true){
    		return true;
    	} else {
    		throw new NullPointerException();
    	}
    }

    /**
     * Take in int i, if greater than 0 and does NOT exist in the collection,
     *  insert i into the collection and increment howmany
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
    	if (i > 0){
    		//Bypasses duplicate values
    		if(belongs(i)){
    			return;
    		}
    		int key = hash(i);
    		
    		if(c.containsKey(key) == false){ //Doesn't contain hash address, empty
    			c.put(key, i);
    		} else { //This means there is already a value in the hash address, linear probe
    			
    		}
    		
    	}
    	
//            int j = 0;
//            while ((j < howmany) && (c[j] != i)) {
//                j++;
//            }
//            if (j == howmany) {
//                if (j == c.length) {
//                    int[] newC = new int[c.length * 2];
//                    for (int index = 0; index < c.length; index++) {
//                        newC[index] = c[index];
//                    }
//                    c = newC;
//                }
//                c[j] = i;
//                howmany++;
//            }
//        }
    }

    /**
     * Take in int i, if i exists in the collection, delete it. decrement howmany
     *
     * @param i positive integer
     */

    public void omit(int i) {
        if (i > 0) {
            int j = 0;
            while ((j < howmany) && (c[j] != i)) {
                j++;
            }
            if (c[j] == i) {
                c[j] = c[howmany - 1];
                howmany--; //Prevents 0s from showing up when omiting
            }
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
     **/
    public void print() {
    	//Get a set of the entries in the hashmap
        Set set = c.entrySet();
        //Get iterator
        Iterator i = set.iterator();
        //Display elements
        while(i.hasNext()){
        	Map.Entry me = (Map.Entry)i.next();
        	System.out.print(me.getKey() + ": ");
        	System.out.print(me.getValue());
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
    public boolean equals(HashCol obj) {
        int j = 0;
        boolean result = obj.get_howmany() == this.get_howmany();
        
        while ((j < howmany) && result) {
            result = obj.belongs(c[j]);
            j++;
        }
        return result;
    }
}

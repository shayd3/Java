//*********************************************************************
// FILE NAME    : Intcoll6.java
// DESCRIPTION  : This file contains the class Intcoll6.
//*********************************************************************
import java.util.*;

public class Intcoll6 {

    private btNode c;
    private int howmany;

    /**
     * Creates a new empty collection of integers
     */
    public Intcoll6() {
        c = null; //Ensures c is a new empty list
        howmany = 0;

    }

    
    /**
     * Creates a new empty collection of integers
     *
     * @param i positive integer
     */
    public Intcoll6(int i) {
        c = null;
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
	public void copy(Intcoll6 obj) {
		if (this != obj){
			howmany = obj.howmany;
			c = copytree(obj.c);
		}
    }
	
	//Extension of copy method
	private static btNode copytree(btNode t){
		btNode root = null;
		if (t != null){
			root = new btNode();
			root.info = t.info;
			root.left = copytree(t.left);
			root.right = copytree(t.right);
		}
		return root;
	}

    /**
     * Pre-condition: takes in integer
     * Post-condition: Checks to see if int i is in the collection
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {
    	btNode p = c;
    	while((p != null) && (p.info != i)){
    		if(i < p.info){
    			p = p.left;
    		} else {
    			p = p.right;
    		}
    	}
    	return (p != null);
    }

    
    /**
     * Take in int i, if greater than 0 and does NOT exist in the collection,
     *  insert i into the collection and increment howmany
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
    	btNode p = c, pred = null;
    	while ((p != null) && (p.info != i)){
    		pred = p;
    		if ( i < p.info){
    			p = p.left;
    		} else {
    			p = p.right;
    		}
    	}
    	if(p == null){
    		howmany++;
    		p = new btNode(i,null,null);
    		if(pred != null){
    			if (i < pred.info){
    				pred.left = p;
    			} else {
    				pred.right = p;
    			} 
    		} else {
    			c = p;
    		}
    	}
    }
    

    /**
     * Take in int i, if i exists in the collection, delete it. decrement howmany
     *
     * @param i positive integer
     */
    public void omit(int i) {
    	if (i > 0){
    		//finds which node contains i
    		btNode p = c, pred = null;
    		while ((p != null) && (p.info != i)){
    			pred = p;
    			if(i < p.info){
    				p = p.left;
    			} else {
    				p = p.right;
    			}
    		}
    		if (p != null){
    			//Delete node with no subtree (no children)
    			if((p.left == null) && (p.right == null)){
    				if(pred == null) {
    					c = null; //Removes root if no children
    				} else if (pred.info > i){//if i > parent, its in the right node. else left
    					pred.left = null;
    				} else{
    					pred.right = null;
    				}
    			} else if ((p.left == null) && (p.right != null)){
    				if(pred == null){
    					c = p.right;
    				} else if (pred.info > i){
    					pred.left = p.right;
    				} else {
    					pred.right = p.right;
    				}
    					
    			} else if ((p.left != null) && (p.right == null)){
    				if (pred == null){
    					c = p.left;
    				} else if(pred.info > i){
    					pred.left = p.left;
    				} else {
    					pred.right = p.left;
    				}
    			} else { //if node has two children
    				if (p.left != null){
    					if(p.right != null){
    						btNode temp = p, last = p;
    						pred = temp.left;
    						temp = temp.left.right;
    						while (temp != null) { //Finds largest node in left subtree
    							last = pred;
    							pred = temp;
    							temp = temp.right;
    						}
    						p.info = pred.info;
    						if (last == p){
    							last.left = pred.left;
    						} else {
    							last.right = pred.left;
    						}
    					}  
    				}
    			}
    			howmany--;
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
     */
    public void print() {
    	printtree(c);
    }
    
    //Extension of print method
    private static void printtree(btNode t){
    	if (t != null){
    		printtree(t.left);
    		System.out.println(t.info);
    		printtree(t.right);
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
    public boolean equals(Intcoll6 obj) {
    	boolean result = howmany == obj.howmany;
    	if((obj.c != null) && result) {
    		int[] a = new int[howmany];
    		int[] b = new int[howmany];
    		toarray(c, a, 0);
    		toarray(obj.c, b, 0);
    		
    		int j = 0;
    		while ((result) && (j < howmany)){
    			result = (a[j] == b[j]);
    			j++;
    		}
    	}
        return result;
    }
    
    private static int toarray(btNode t, int[] a, int i){
    	int num_nodes=0;
    	if (t != null){
    		num_nodes = toarray(t.left, a, i);
    		a[num_nodes+i] = t.info;
    		num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes+i+1);
    	}
    	return num_nodes;
    }
    
    private static class btNode
    {
         private int info;
         private btNode left, right;

         public btNode()
         {
             info=0; left=right=null;
         }

         public btNode(int i, btNode lt, btNode rt)
         {
             info=i; left=lt; right=rt;
         }
   }
}

//*********************************************************************
// FILE NAME    : Stringcoll.java
// DESCRIPTION  : This file contains the class Stringcoll.
//*********************************************************************

import java.util.*;

public class StringColl {

    private btNode c;
    private int howmany;

    /**
     * Creates a new empty collection of integers
     */
    public StringColl() {
        c = null; //Ensures c is a new empty list
        howmany = 0;

    }

    
    /**
     * Creates a new empty collection of integers
     *
     * @param i positive integer
     */
    public StringColl(String s) {
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
	public void copy(StringColl obj) {
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
    public boolean belongs(String s) {
    	btNode p = c;
    	while((p != null) && (p.info != s)){
    		if(p.info.compareTo(s) > 0){
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
    public void insert(String s) {
    	if(s.substring(0).contains("-")){
    		s = s.replace("-","");
    	}
        if(s.substring(0).contains("+")){
    		s = s.replace("+","");
    	}
    	btNode p = c, pred = null;
    	while ((p != null) && !(p.info.compareTo(s) == 0)){
    		pred = p;
    		if ( p.info.compareTo(s) > 0){
    			p = p.left;
    		} else {
    			p = p.right;
    		}
    	}
    	if(p == null){
    		howmany++;
    		p = new btNode(s,null,null);
    		if(pred != null){
    			if (pred.info.compareTo(s)>0){
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
    public void omit(String s) {
        if(s.substring(0).contains("-")){
    		s = s.replace("-","");
    	}
        if(s.substring(0).contains("+")){
    		s = s.replace("+","");
    	}
    	if (!s.equals(null)){
    		//finds which node contains i
    		btNode p = c, pred = null;
//                System.out.println("P.info: " +p.info);
//                System.out.println("s: " + s);
//                System.out.println("p.info.compareto(S):" + p.info.compareTo(s));
//                System.out.println("p: " + p);
    		while ((p != null) && !(p.info.compareTo(s) == 0)){
    			pred = p;
    			if(p.info.compareTo(s) > 0){
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
    				} else if (pred.info.compareTo(s) > 0/*pred.info > i*/){//if i > parent, its in the right node. else left
    					pred.left = null;
    				} else{
    					pred.right = null;
    				}
    			} else if ((p.left == null) && (p.right != null)){
    				if(pred == null){
    					c = p.right;
    				} else if (pred.info.compareTo(s) > 0){
    					pred.left = p.right;
    				} else {
    					pred.right = p.right;
    				}
    					
    			} else if ((p.left != null) && (p.right == null)){
    				if (pred == null){
    					c = p.left;
    				} else if(pred.info.compareTo(s) > 0){
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
    public boolean equals(StringColl obj) {
    	boolean result = howmany == obj.howmany;
    	if((obj.c != null) && result) {
    		String[] a = new String[howmany];
    		String[] b = new String[howmany];
    		toarray(c, a, 0);
    		toarray(obj.c, b, 0);
    		
    		int j = 0;
    		while ((result) && (j < howmany)){
    			result = (a[j].equals(b[j]));
    			j++;
    		}
    	}
        return result;
    }
    
    private static int toarray(btNode t, String[] a, int i){
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
         private String info;
         private btNode left, right;

         public btNode()
         {
             info=null; left=right=null;
         }

         public btNode(String i, btNode lt, btNode rt)
         {
             info=i; left=lt; right=rt;
         }
   }
}

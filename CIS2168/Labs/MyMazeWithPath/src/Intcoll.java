//*********************************************************************
// FILE NAME    : Intcoll1.java
// DESCRIPTION  : This file contains the class Intcoll1.
//*********************************************************************

import java.util.*;

public class Intcoll {

    private int[] c;

    /**
     * Creates a new array c with the size 500+1 Sets index 0 of the array to 0
     */
    public Intcoll() {
        c = new int[500 + 1];
        c[0] = 0;
    }

    /**
     * Creates a new array with the size i
     *
     * @param i positive integer
     */
    public Intcoll(int i) {
        c = new int[i + 1];
        c[0] = 0;
    }

    /**
     * Pre-condition: Takes in an object of Intcoll1 
     * Post-condition: If obj does not equal c, create a new array and copy all 
     *  elements from c to obj
     *
     * @param obj
     */
    public void copy(Intcoll obj) {
        if (this != obj) {
            c = new int[obj.c.length];
            int j = 0;
            while (obj.c[j] != 0) {
                c[j] = obj.c[j];
                j++;
            }
            c[j] = 0;
        }
    }

    /**
     * Pre-condition: Take in positive integer i 
     * Post-condition: Return true if i is greater than 0 and i is equal to 
     * element in array. Otherwise, false
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {
        int j = 0;
        while ((c[j] != 0) && (c[j] != i)) {
            j++;
        }
        return ((i > 0) && (c[j] == i));
    }

    /**
     * Pre-condition: Take in a positive integer i 
     * Post-condition: If i is greater than 0 and is not in the current collection, insert 
     * into collection. Otherwise, do nothing. If current array is too small to
     * insert new integer, create new array double the size and copy all
     * elements into new array. Redirect new array to old reference variable.
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
        if (i > 0) {
            int j = 0;
            while ((c[j] != 0) && (c[j] != i)) {
                j++;
            }
            if (c[j] == 0) {
                if (j == c.length - 1) {
                    int[] newC = new int[c.length * 2];
                    for (int index = 0; index < c.length; i++) {
                        newC[index] = c[index];
                    }
                    c = newC;
                }
                c[j] = i;
                c[j + 1] = 0;
            }
        }
    }

    /**
     * if i is greater than 0, then scan array. If i is found in the array, take
     * away and move any element after the index to fill in the 0 space
     *
     * @param i positive integer
     */

    public void omit(int i) {
        if (i > 0) {
            int j = 0;
            while ((c[j] != 0) && (c[j] != i)) {
                j++;
            }
            if (c[j] == i) {
                int k = j + 1;
                while (c[k] != 0) {
                    k++;
                }
                c[j] = c[k - 1];
                c[k - 1] = 0;
            }
        }
    }

    /**
     * Returns number of integers in a collection until loop hits 0
     *
     * @return howmany
     */
    public int get_howmany() {
        int j = 0, howmany = 0;

        while (c[j] != 0) {
            howmany++;
            j++;
        }
        return howmany;
    }

    /**
     * Prints out the entire collection until it reaches an element of 0
     */
    public void print() {
        int j = 0;
        System.out.println();
        while (c[j] != 0) {
            System.out.println(c[j]);
            j++;
        }
    }

    /**
     * Pre-condition: Takes in an existing object of Intcoll1 Post-condition:
     * Compares two objects, if both collections are equal, return true.
     * Otherwise, false.
     *
     * @param obj object containing an array
     * @return result
     */
    public boolean equals(Intcoll obj) {
        int j = 0;
        boolean result = true;
        while ((c[j] != 0) && result) {
            result = obj.belongs(c[j]);
            j++;
        }
        j = 0;
        while ((obj.c[j] != 0) && result) {
            result = belongs(obj.c[j]);
            j++;
        }
        return result;
    }
}


////*********************************************************************
//// FILE NAME    : Intcoll6.java
//// DESCRIPTION  : This file contains the class Intcoll6.
////*********************************************************************
//import java.util.*;
//
//public class Intcoll {
//
//    private btNode c;
//    private int howmany;
//
//    /**
//     * Creates a new empty collection of integers
//     */
//    public Intcoll() {
//        c = null; //Ensures c is a new empty list
//        howmany = 0;
//
//    }
//
//    
//    /**
//     * Creates a new empty collection of integers
//     *
//     * @param i positive integer
//     */
//    public Intcoll(int i) {
//        c = null;
//        howmany = 0;
//    }
//
//    
//    /**
//     * Pre-condition: Take in an object of Intcoll
//     * Post-condition: Takes in an object, if parameter is not empty,
//     * 		copy howmany and all information from object that is 
//     * 		calling method to the parameter
//     * 
//     * @param obj
//     */
//	public void copy(Intcoll obj) {
//		if (this != obj){
//			howmany = obj.howmany;
//			c = copytree(obj.c);
//		}
//    }
//	
//	//Extension of copy method
//	private static btNode copytree(btNode t){
//		btNode root = null;
//		if (t != null){
//			root = new btNode();
//			root.info = t.info;
//			root.left = copytree(t.left);
//			root.right = copytree(t.right);
//		}
//		return root;
//	}
//
//    /**
//     * Pre-condition: takes in integer
//     * Post-condition: Checks to see if int i is in the collection
//     *
//     * @param i positive integer
//     * @return
//     */
//    public boolean belongs(int i) {
//    	btNode p = c;
//    	while((p != null) && (p.info != i)){
//    		if(i < p.info){
//    			p = p.left;
//    		} else {
//    			p = p.right;
//    		}
//    	}
//    	return (p != null);
//    }
//
//    
//    /**
//     * Take in int i, if greater than 0 and does NOT exist in the collection,
//     *  insert i into the collection and increment howmany
//     *
//     * @param i positive integer to insert in object
//     */
//    public void insert(int i) {
//    	btNode p = c, pred = null;
//    	while ((p != null) && (p.info != i)){
//    		pred = p;
//    		if ( i < p.info){
//    			p = p.left;
//    		} else {
//    			p = p.right;
//    		}
//    	}
//    	if(p == null){
//    		howmany++;
//    		p = new btNode(i,null,null);
//    		if(pred != null){
//    			if (i < pred.info){
//    				pred.left = p;
//    			} else {
//    				pred.right = p;
//    			} 
//    		} else {
//    			c = p;
//    		}
//    	}
//    }
//    
//
//    /**
//     * Take in int i, if i exists in the collection, delete it. decrement howmany
//     *
//     * @param i positive integer
//     */
//    public void omit(int i) {
//    	if (i > 0){
//    		//finds which node contains i
//    		btNode p = c, pred = null;
//    		while ((p != null) && (p.info != i)){
//    			pred = p;
//    			if(i < p.info){
//    				p = p.left;
//    			} else {
//    				p = p.right;
//    			}
//    		}
//    		if (p != null){
//    			//Delete node with no subtree (no children)
//    			if((p.left == null) && (p.right == null)){
//    				if(pred == null) {
//    					c = null; //Removes root if no children
//    				} else if (pred.info > i){//if i > parent, its in the right node. else left
//    					pred.left = null;
//    				} else{
//    					pred.right = null;
//    				}
//    			} else if ((p.left == null) && (p.right != null)){
//    				if(pred == null){
//    					c = p.right;
//    				} else if (pred.info > i){
//    					pred.left = p.right;
//    				} else {
//    					pred.right = p.right;
//    				}
//    					
//    			} else if ((p.left != null) && (p.right == null)){
//    				if (pred == null){
//    					c = p.left;
//    				} else if(pred.info > i){
//    					pred.left = p.left;
//    				} else {
//    					pred.right = p.left;
//    				}
//    			} else { //if node has two children
//    				if (p.left != null){
//    					if(p.right != null){
//    						btNode temp = p, last = p;
//    						pred = temp.left;
//    						temp = temp.left.right;
//    						while (temp != null) { //Finds largest node in left subtree
//    							last = pred;
//    							pred = temp;
//    							temp = temp.right;
//    						}
//    						p.info = pred.info;
//    						if (last == p){
//    							last.left = pred.left;
//    						} else {
//    							last.right = pred.left;
//    						}
//    					}  
//    				}
//    			}
//    			howmany--;
//    		}
//    	}
//    }
//    
//
//    /**
//     * Returns number of integers in collection
//     *
//     * @return howmany
//     */
//    public int get_howmany() {
//        return howmany;
//    }
//    
//
//    /**
//     * Prints out the entire collection
//     */
//    public void print() {
//    	printtree(c);
//    }
//    
//    //Extension of print method
//    private static void printtree(btNode t){
//    	if (t != null){
//    		printtree(t.left);
//    		System.out.println(t.info);
//    		printtree(t.right);
//    	}
//    }
//
//    /**
//     * Pre-condition: Takes in an object of Intcoll
//     * Post-Condition: If both objects contain the same amount of integers
//     * in the collection, compare integers inside of collection. If everything
//     * is the same, return true. Otherwise false.
//     *
//     * @param obj object containing an array
//     * @return result
//     */
//    public boolean equals(Intcoll obj) {
//    	boolean result = howmany == obj.howmany;
//    	if((obj.c != null) && result) {
//    		int[] a = new int[howmany];
//    		int[] b = new int[howmany];
//    		toarray(c, a, 0);
//    		toarray(obj.c, b, 0);
//    		
//    		int j = 0;
//    		while ((result) && (j < howmany)){
//    			result = (a[j] == b[j]);
//    			j++;
//    		}
//    	}
//        return result;
//    }
//    
//    private static int toarray(btNode t, int[] a, int i){
//    	int num_nodes=0;
//    	if (t != null){
//    		num_nodes = toarray(t.left, a, i);
//    		a[num_nodes+i] = t.info;
//    		num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes+i+1);
//    	}
//    	return num_nodes;
//    }
//    
//    private static class btNode
//    {
//         private int info;
//         private btNode left, right;
//
//         public btNode()
//         {
//             info=0; left=right=null;
//         }
//
//         public btNode(int i, btNode lt, btNode rt)
//         {
//             info=i; left=lt; right=rt;
//         }
//   }
//}

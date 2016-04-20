//*********************************************************************
// FILE NAME    : Intcoll2.java
// DESCRIPTION  : This file contains the class Intcoll1.
//*********************************************************************

import java.util.*;

public class Intcoll2 {

    private int[] c;
    private int howmany = 0;

    /**
     * Creates a new array c with the size 500
     */
    public Intcoll2() {
        c = new int[500];
    }

    /**
     * Creates a new array with the size i
     *
     * @param i positive integer
     */
    public Intcoll2(int i) {
        c = new int[i];
    }

    /**
     * Pre-condition: Takes in an object of Intcoll2 
     * Post-condition: If obj does not equal c, create a new array and copy 
     * all elements from c to obj
     *
     * @param obj
     */
    public void copy(Intcoll2 obj) {
        if (this != obj) {
            c = new int[obj.c.length];
            howmany = obj.get_howmany();
            int j = 0;
            while (j < this.get_howmany()) {
                c[j] = obj.c[j];
                j++;
            }
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
        while ((j < howmany) && (c[j] != i)) {
            j++;
        }
        return (c[j] == i);
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
            while ((j < howmany) && (c[j] != i)) {
                j++;
            }
            if (j == howmany) {
                if (j == c.length) {
                    int[] newC = new int[c.length * 2];
                    for (int index = 0; index < c.length; index++) {
                        newC[index] = c[index];
                    }
                    c = newC;
                }
                c[j] = i;
                howmany++;
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
     * Returns number of integers in a collection until loop hits 0
     *
     * @return howmany
     */
    public int get_howmany() {
        return howmany;
    }

    /**
     * Prints out the entire collection until it reaches an element of 0
     */
    public void print() {
        int j = 0;
        System.out.println();
        while (j < howmany) {
            System.out.println(c[j]);
            j++;
        }
    }

    /**
     * Pre-condition: Takes in an existing object of Intcoll2 Post-condition:
     * Compares two objects, if both collections are equal, return true.
     * Otherwise, false.
     *
     * @param obj object containing an array
     * @return result
     */
    public boolean equals(Intcoll2 obj) {
        int j = 0;
        boolean result = obj.get_howmany() == this.get_howmany();
        
        while ((j < howmany) && result) {
            result = obj.belongs(c[j]);
            j++;
        }
        return result;
    }
}

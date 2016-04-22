//*********************************************************************
// FILE NAME    : Intcoll1.java
// DESCRIPTION  : This file contains the class Intcoll1.
//*********************************************************************

import java.util.*;

public class Intcoll1 {

    private int[] c;

    /**
     * Creates a new array c with the size 500+1 Sets index 0 of the array to 0
     */
    public Intcoll1() {
        c = new int[500 + 1];
        c[0] = 0;
    }

    /**
     * Creates a new array with the size i
     *
     * @param i positive integer
     */
    public Intcoll1(int i) {
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
    public void copy(Intcoll1 obj) {
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
    public boolean equals(Intcoll1 obj) {
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

//*********************************************************************
// FILE NAME    : Intcoll3.java
// DESCRIPTION  : This file contains the class Intcoll3.
//*********************************************************************

import java.util.*;

public class Intcoll3 {

    private boolean[] c;
    private int howmany = 0;

    /**
     * Creates a new array c with the size 500
     */
    public Intcoll3() {
        c = new boolean[500 + 1]; //skips 0
        howmany = 0;

    }

    /**
     * Creates a new array with the size i
     *
     * @param i positive integer
     */
    public Intcoll3(int i) {
        c = new boolean[i + 1];
        howmany = 0;
    }

    /**
     * Pre-condition: Takes in an object of Intcoll3 Post-condition: If obj does
     * not equal c, create a new array and copy all elements from c to obj
     *
     * @param obj
     */
    public void copy(Intcoll3 obj) {
        if (this != obj) {
            c = new boolean[obj.c.length];
            howmany = obj.get_howmany();
            int j = 1;
            while (j < c.length) {
                c[j] = obj.c[j];
                j++;
            }
        }
    }

    /**
     * Pre-condition: Take in positive integer i Post-condition: Return true if
     * i is greater than 0 and i is equal to element in array. Otherwise, false
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {
        if (i > 0) {
            if ((i < c.length) && (c[i] == true)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Pre-condition: Take in a positive integer i Post-condition: If i is
     * greater than 0 and is not in the current collection, insert into
     * collection. Otherwise, do nothing. If current array is too small to
     * insert new integer, create new array double the size and copy all
     * elements into new array. Redirect new array to old reference variable.
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
        if (i > 0) {
            if (i >= c.length) {
                boolean[] newC = new boolean[i * 2];
                for (int index = 0; index < c.length; index++) {
                    newC[index] = c[index];
                }
                c = newC;
            }
            c[i] = true;
            howmany++;
        }
    }

    /**
     * if i is greater than 0, then scan array. If i is found in the array, take
     * away and move any element after the index to fill in the 0 space
     *
     * @param i positive integer
     */
    public void omit(int i) {
        if ((i > 0) && (i < c.length)) {
            if (c[i] == true) {
                c[i] = false;
                howmany--;
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
        for (int i = 1; i < c.length; i++) {
                if (c[i] == true) {
                    System.out.println(i);
                }
        }
    }

    /**
     * Pre-condition: Takes in an existing object of Intcoll3 Post-condition:
     * Compares two objects, if both collections are equal, return true.
     * Otherwise, false.
     *
     * @param obj object containing an array
     * @return result
     */
    public boolean equals(Intcoll3 obj) {
        boolean result = obj.get_howmany() == this.get_howmany();
        int j = 1;
        while ((result) && (j < c.length)) {
            if (c[j] == true) {
                result = obj.belongs(j);
            }
            j++;
        }
        return result;
    }
}

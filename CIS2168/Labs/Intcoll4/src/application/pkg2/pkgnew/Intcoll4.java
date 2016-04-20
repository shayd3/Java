//*********************************************************************
// FILE NAME    : Intcoll4.java
// DESCRIPTION  : This file contains the class Intcoll4.
//*********************************************************************
package application.pkg2.pkgnew;

//import java.util.*;
public class Intcoll4 {

    private ListNode c;
    private int howmany;

    /**
     * Create a new LinkedList with c pointing to null
     */
    public Intcoll4() {
        c = null;
        howmany = 0;

    }

    /**
     * Create a new LinkedList with c pointing to null
     *
     * @param i positive integer
     */
    public Intcoll4(int i) {
        c = null;
        howmany = 0;
    }

    public void ReverseL(Intcoll4 L) {
        ListNode p;
        ListNode RevL = c;
        int index = L.get_howmany();
        if (L.c != null) {
            while (this.get_howmany() != L.get_howmany()) {
                p = L.c;
                for (int i = index; i <= 0; i--) {
                    p = p.link;
                }
                RevL = new ListNode(p.info, RevL);
                howmany++;
                index--;
            }
        }
        c = RevL;
    }

    /**
     * Pre-condition: Take in an object of Intcoll4 Post-condition: Copy howmany
     * from parameter to new copy if obj does not equal null (empty) then copy
     * to new LinkedList. If it does equal null, set new copy to null. (Prevents
     * error)
     *
     * @param obj
     */
    public void copy(Intcoll4 obj) {
        if (this != obj) {
            howmany = obj.get_howmany();
            c = new ListNode();
            ListNode p1 = obj.c; //Temp node for parameter
            ListNode p2 = c; //Temp node for c
            if (obj.c != null) { //If parameter is empty, do nothing and set c to null
                p2.info = p1.info; //Changing first obj of info from p1 to p2
                p1 = p1.link; //Point to next link in LinkedList
                while (p1 != null) {//Copying p1 to p2
                    p2.link = new ListNode();
                    p2 = p2.link;
                    p2.info = p1.info;
                    p1 = p1.link;
                }
            } else {
                c = null;
            }
        }
    }

    /**
     * Pre-condition: takes in integer Post-condition: Checks to see if i
     * belongs in the link list. Otherwise, false.
     *
     * @param i positive integer
     * @return
     */
    public boolean belongs(int i) {
        ListNode p = c;
        if (i > 0) {
            while ((p != null) && (p.info != i)) {
                p = p.link;
            }
        }
        return (p != null);
    }

    /**
     * If next link in LinkedList points to a null value, point p to i for the
     * next link. Increment howmany
     *
     * @param i positive integer to insert in object
     */
    public void insert(int i) {
        if (i > 0) {
            ListNode p = c; //p is a temp node
            while ((p != null) && (p.info != i)) {  //Checks if empty and bypasses duplicates
                p = p.link; //Move to next link
            }
            if (p == null) {
                p = new ListNode(i, c);
                p.link = c;
                c = p;
                howmany++;
            }
        }
    }

    /**
     * Take in integer i. Keep track of previous link prev and current link p.
     * Changes pointers to next pointer in the LinkedList and replaces link
     * containing i to null.
     *
     * @param i positive integer
     */
    public void omit(int i) {
        if (i > 0) {
            ListNode p = c;
            ListNode prev = null;
            while ((p != null) && (p.info != i)) {
                prev = p;
                p = p.link;
            }
            if (p != null) {
                if (prev == null) {
                    c = p.link;
                } else {
                    prev.link = p.link;
                }
                howmany--;
            }
        }
    }

    /**
     * Returns number of integers in LinkedList
     *
     * @return howmany
     */
    public int get_howmany() {
        return howmany;
    }

    /**
     * Prints out the entire collection until p points to null value
     */
    public void print() {
        ListNode p = c;
        System.out.println();
        while (p != null) {
            System.out.println(p.info);
            p = p.link;
        }
    }

    /**
     * Pre-condition: Takes in an object of Intcoll4 Post-Condition: Compares
     * the two objects to see if integers inside of linked list are the same and
     * are actually in the list
     *
     * @param obj object containing an array
     * @return result
     */
    public boolean equals(Intcoll4 obj) {
        boolean result = obj.get_howmany() == this.get_howmany();
        ListNode p = obj.c;
        while ((p != null) && result) {
            result = belongs(p.info);
            p = p.link;
        }
        return result;
    }

    //Inner class for ListNode
    private class ListNode {

        private int info;
        private ListNode link;

        public ListNode() {
            info = 0;
            link = null;
        }

        public ListNode(int i, ListNode next) {
            info = i;
            link = next;
        }
    }
}

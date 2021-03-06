//**********************************************************
// FILE: NAME   : StringCollClient.java
// DESCRIPTION  : This is a client of class StringCollClient.
//**********************************************************

import java.util.*;

public class MultiStringCollClient {

    public static final String SENTINEL = "###";

    public static void main(String[] args) {

        String value;
        Scanner keyboard = new Scanner(System.in);
        MultiStringColl P = new MultiStringColl(), N = new MultiStringColl(), L = new MultiStringColl();

        System.out.println("Enter a string to be inserted or type ### to quit:");
        value = keyboard.nextLine();
        
        while (!(value.equals(SENTINEL))) {
            if (!(value.substring(0).contains("-"))) {
                P.insert(value);
                L.insert(value);
            } else {
               N.insert(value);
               L.omit(value);
            }
//            System.out.println("Enter next string to be inserted or type ### to quit:");
            value = keyboard.nextLine();
        }
       
        System.out.println("\nThe values in collection P are:");
        P.print();
        System.out.println("\nThe values in collection N are:");
        N.print();
        System.out.println("\nThe values in collection L are:");
        L.print();
        if (P.equals(N)) {
            System.out.println("\nP and N are equal.");
        } else {
            System.out.println("\nP and N are NOT equal.");
        }
        MultiStringColl A = new MultiStringColl();
        A.copy(L);
        System.out.println("\nThe values in the copy of L are:\n");
        A.print();
    }
}

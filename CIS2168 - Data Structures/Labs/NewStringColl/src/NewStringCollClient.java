//**********************************************************
// FILE: NAME   : NewStringCollClient.java
// DESCRIPTION  : This is a client of class NewStringCollClient.
//**********************************************************

import java.util.*;

public class NewStringCollClient {

    public static final String SENTINEL = "###";

    public static void main(String[] args) {

        String value;
        Scanner keyboard = new Scanner(System.in);
        NewStringColl P = new NewStringColl(), N = new NewStringColl(), L = new NewStringColl();

        System.out.println("Enter a string to be inserted or type ### to quit:");
        value = keyboard.nextLine();
        
        while (!(value.equals(SENTINEL))) {
            if ((value.substring(0).contains("+"))) {
                P.insert(value.substring(1));
                L.insert(value.substring(1));
            } else if (value.substring(0).contains("-")) {
               N.insert(value.substring(1));
               L.omit(value.substring(1));
            }
            System.out.println("Enter next string to be inserted or type ### to quit:");
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
        NewStringColl A = new NewStringColl();
        A.copy(L);
        System.out.println("\nThe values in the copy of L are:\n");
        A.print();
    }
}

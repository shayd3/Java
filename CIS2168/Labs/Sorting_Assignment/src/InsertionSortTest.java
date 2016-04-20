
import java.util.Random;
import java.util.Scanner;

public class InsertionSortTest {

   static int icount = 0, k = 0, arraySize,  max = 0, min = 0, avg = 0, sum = 0, input;

    static String choice;

    public static void main(String[] args) {

        System.out.println("WELCOME TO QuickSortTest!\n");
        Scanner console = new Scanner(System.in);

        //Trust user input for all
        System.out.println("What array size would you like? (Recommended: 2048, 4096, 8192, 16384): ");
        arraySize = console.nextInt();

        System.out.println("How many times to repeat the test? ");
        k = console.nextInt();

        System.out.println("Would you like to auto-populate the array? (Recommened for large array sizes): ");
        choice = console.next();

        int[] a = new int[arraySize];

        if (choice.equalsIgnoreCase("Yes")) {
            populateArray(a);
        } else {
            System.out.println("Enter integers to input into array: ");
            for (int i = 0; i < a.length; i++) {
                System.out.print("Index " + i + ": ");
                input = console.nextInt();
                a[i] = input;
            }
        }
        
        //repeats
        for (int i = 1; i <= k; i++) {
            System.out.println("*************** Repeat #" + i + " ***************");
            
            System.out.println("Before sort: ");
            print(a);
            insertion(a);

            if(icount > max){
                max = icount;
            } 
            if(i == 1){ //Sets min to max on the first run through
                min = max;
            }
            if(icount < min){
                min = icount;
            }
            sum = sum + icount;
            System.out.println("After Sort: ");
            print(a);
            System.out.println("Sorted? " + verifySort(a));
            if (choice.equalsIgnoreCase("Yes")){
                populateArray(a);
            }
            icount = 0;
        }
        avg = sum/k;
        System.out.println("Min: " + min + "\nMax: " + max + "\nAvg: " + avg);
    }
    
    /*
     CHECKS IF ARRAY IS SORTED
     */
    public static boolean verifySort(int[] a) {
        boolean sorted = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                sorted = false;
            }
        }
        return sorted;
    }

    /*
     * INSERTION SORT
     */
    private static void insertion(int[] a) {
        int temp, j;

        for (int i = 1; i < a.length; i++) {
            for (j = i; (j > 0) && (a[j - 1] > a[j]); j--) {
                icount++;
                //Stores current int, move smaller int to cur, move cut to previous
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

    /*
     Prints the array being passed through
     */
    private static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }

    /*
     * Create an array of type int and populate with random
     * numbers
     */
    private static void populateArray(int[] a) {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(5000) + 1;
        }
    }
}

/*
 Name: Ryan Ross

 Hangman game using get request to a random word generator
 */
package hangman;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Hangman {

    private static final char HIDECHAR = '_';
    static String randomWord, preGuess;
    static char guess;
    static char[] charArray = randomWord.toCharArray();
    static String hidden = createHidden();
    static int count = 10; //Each game you get 10 tries to guess the word
    char[] guesses = new char[26];
    static boolean solved = false;

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        sendGet();

        System.out.println("//////////////////////////////"
                + "\n///// WELCOME TO HANGMAN /////"
                + "\n//////////////////////////////");
        do {
            System.out.println("Word length: " + randomWord.length());
            //Write code to display _ _ _ _ _ instead of letters, reveal once guessed right
            System.out.println("What is your guess?> ");
            preGuess = console.nextLine();
            guess = preGuess.charAt(0);
            if(!contains(charArray, guess)){ //Scans the random word, if letter not contained, lose a life
                count--;
                if(count == 0){
                    System.out.println("Game over! Thanks for playing!");
                    System.exit(0);
                }
            } else {
                
            }
        } while (!solved);
    }
    
    private static String createHidden() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomWord.length(); i++) {
            sb.append(HIDECHAR);
        }
        return sb.toString();
    }
    
    public static boolean contains(char[] randomWord, char input){
        int j = 0;
        while(randomWord[j] != input){
            j++;
        }
        return(randomWord[j]==input);
    }
    
    private static void sendGet() throws Exception {
        String url = "http://randomword.setgetgo.com/get.php";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        randomWord = response.toString().toLowerCase();

    }
}

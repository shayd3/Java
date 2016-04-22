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

    static String randomWord;
    char[] charArray = randomWord.toCharArray();
    int count = 10; //Each game you get 10 tries to guess the word
    char[] guesses = new char[26];

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        sendGet();

        System.out.println("//////////////////////////////"
                + "\n///// WELCOME TO HANGMAN /////"
                + "\n//////////////////////////////");
        
        System.out.println("Word length: " + randomWord.length());
        
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

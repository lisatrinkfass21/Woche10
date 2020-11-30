/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beispiel1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 *
 * @author Lisa
 */
public class Beispiel1 {

    private static final int NUMBER_THREADS = 6;
    private static final File path0 = new File("password0");
    private static final File path1 = new File("password1");
    private static final File path2 = new File("password2");
    private static final File path3 = new File("password3");
    public static final String[] kleinbuchstaben = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static final String[] großbuchstaben = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "F", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static final String[] klgrza = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "F", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static List<String> fabelwesen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, MalformedURLException, IOException, ParserException {

        ExecutorService ex = Executors.newFixedThreadPool(NUMBER_THREADS);
        BufferedReader reader;
        String hash0 = null;
        String hash1 = null;
        String hash2 = null;
        String hash3 = null;
        try {
            reader = new BufferedReader(new FileReader(path0));
            hash0 = reader.readLine();

            reader = new BufferedReader(new FileReader(path1));
            hash1 = reader.readLine();

            reader = new BufferedReader(new FileReader(path2));
            hash2 = reader.readLine();

            reader = new BufferedReader(new FileReader(path3));
            hash3 = reader.readLine();

            reader.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Beispiel1.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException exep) {
            Logger.getLogger(Beispiel1.class.getName()).log(Level.SEVERE, null, exep);
        }

        //passowrd0
        int value = kleinbuchstaben.length / NUMBER_THREADS;
        List<FindPassword0> tasks = new ArrayList<>();
        int begin = 0;
        for (int i = 0; i < NUMBER_THREADS; i++) {
            tasks.add(new FindPassword0(hash0, begin, begin + value));
            begin += value;
        }

        List<Future<String>> futures = ex.invokeAll(tasks);
        for (Future<String> future : futures) {
            if (future.get() != null) {
                System.out.println("Passwort 0: " + future.get());
            }
        }
        /*
        //Passwort1
        value = großbuchstaben.length / NUMBER_THREADS;
        List<FindPassword1> tasks2 = new ArrayList<>();
        begin = 0;
        for (int i = 0; i < NUMBER_THREADS; i++) {
            tasks2.add(new FindPassword1(hash1, begin, begin + value));
            begin += value;
        }
        futures = ex.invokeAll(tasks2);
        for (Future<String> future : futures) {
            if (future.get() != null) {
                System.out.println("Passwort 1: " + future.get());
            }
        }
         */
        //Passwort2
        value = klgrza.length / NUMBER_THREADS;
        List<FindPassword2> tasks3 = new ArrayList<>();
        begin = 0;
        for (int i = 0; i < NUMBER_THREADS; i++) {
            tasks3.add(new FindPassword2(hash2, begin, begin + value));
            begin += value;
        }
        futures = ex.invokeAll(tasks3);
        for (Future<String> future : futures) {
            if (future.get() != null) {
                System.out.println("Passwort 2: " + future.get());
            }
        }
//Passwort3
        String link = "https://de.wikipedia.org/wiki/Liste_von_Fabelwesen";
        Parser parser = new Parser(new URL(link).openConnection());
        NodeFilter filter = new NodeClassFilter(LinkTag.class);
        fabelwesen = new ArrayList<>();
        NodeList nl = parser.extractAllNodesThatMatch(filter);
        for (int i = 0; i < nl.size(); i++) {
            String x = nl.elementAt(i).getText();
            if (x.contains("wiki")) {
                int index = x.indexOf("title=");

                fabelwesen.add(x.substring(index + 7, x.length() - 1));

            }
        }
        value = fabelwesen.size() / NUMBER_THREADS;
        List<FindPassword3> tasks4 = new ArrayList<>();
        begin = 0;
        for (int i = 0; i < NUMBER_THREADS; i++) {
            tasks4.add(new FindPassword3(hash3, begin, begin + value));
            begin += value;
        }
        futures = ex.invokeAll(tasks4);
        for (Future<String> future : futures) {
            if (future.get() != null) {
                System.out.println("Passwort 3: " + future.get());
            }
        }
        ex.shutdown();

    }

}

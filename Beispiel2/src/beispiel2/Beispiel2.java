/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beispiel2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lisa
 */
public class Beispiel2 {

    private final static File file = new File("numbers3");

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int value = 0;
        List<Integer> numbers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            value = Integer.parseInt(br.readLine());
            String text = br.readLine();
            while (text != null) {
                String[] x = text.split(" ");
                for (int i = 0; i < x.length; i++) {
                    numbers.add(Integer.parseInt(x[i]));

                }
                text = br.readLine();
            }

        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Beispiel2.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex1) {
            Logger.getLogger(Beispiel2.class.getName()).log(Level.SEVERE, null, ex1);
        }

        int anzahlThreads = numbers.size() - value + 1; // Anzahl der ausgebenden Zeilen in createSubString
        ExecutorService ex = Executors.newFixedThreadPool(anzahlThreads);
        List<createSubstring> tasks = new ArrayList<>();
        for (int i = 0; i < anzahlThreads; i++) {
            tasks.add(new createSubstring(numbers, value, i));
        }
        //System.out.println(anzahlThreads);
        /*for (Integer task : numbers) {
            System.out.println(task);

        }*/

        List<Future<Integer>> sizes = ex.invokeAll(tasks);
        int max = Integer.MIN_VALUE;
        for (Future<Integer> size : sizes) {
            if (size.get() > max) {
                max = size.get();
            }
        }
        ex.shutdown();

        System.out.println("Die maximale Anzahl an unterschiedlichen Nummern ist: " + max);

    }

}

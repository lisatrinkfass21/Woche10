/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beispiel2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *
 * @author Lisa
 */
public class createSubstring implements Callable<Integer> {

    private List<Integer> numbers;
    private int value;
    private int start;

    public createSubstring(List<Integer> numbers, int value, int start) {
        this.numbers = numbers;
        this.value = value;
        this.start = start;
    }

    @Override
    public Integer call() throws Exception {
        List<Integer> sublist = numbers.subList(start, start + value);
        Set<Integer> set = new HashSet<>();
        set.addAll(sublist);
        print(sublist, set.size());
        return set.size();
    }

    private synchronized void print(List<Integer> list, int count) {
        System.out.println("");
        System.out.print("<");
        for (Integer integer : list) {
            System.out.print(integer + ", ");
        }
        System.out.print("> - Hat " + count + " unterschiedlichen Zahlen");
        System.out.println("");
    }

}

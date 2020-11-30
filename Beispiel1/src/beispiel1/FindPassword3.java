/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beispiel1;

import java.util.concurrent.Callable;

/**
 *
 * @author Lisa
 */
public class FindPassword3 implements Callable<String> {

    String hashPassword;
    int start;
    int end;

    public FindPassword3(String hashPassword, int start, int end) {
        this.hashPassword = hashPassword;
        this.start = start;
        this.end = end;
    }

    @Override
    public String call() throws Exception {
        String x = null;
        String hash = null;
        for (int i = start; i < end; i++) {
            x = Beispiel1.fabelwesen.get(i);
            if (StringUtil.applySha256(x).equals(this.hashPassword)) {
                return x;
            }
        }
        return null;

    }
}

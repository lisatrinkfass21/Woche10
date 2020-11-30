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
public class FindPassword0 implements Callable<String> {

    String hashPassword;

    int start;
    int end;

    public FindPassword0(String hashPassword, int start, int end) {
        this.hashPassword = hashPassword;
        this.start = start;
        this.end = end;

    }

    @Override
    public String call() throws Exception {
        if (this.hashPassword != null) {
            String hash;
            String tmp;
            for (int i = start; i < end; i++) {
                for (int j = 0; j < Beispiel1.kleinbuchstaben.length; j++) {
                    for (int k = 0; k < Beispiel1.kleinbuchstaben.length; k++) {
                        for (int l = 0; l < Beispiel1.kleinbuchstaben.length; l++) {
                            tmp = Beispiel1.kleinbuchstaben[i] + Beispiel1.kleinbuchstaben[j] + Beispiel1.kleinbuchstaben[k] + Beispiel1.kleinbuchstaben[l];
                            hash = StringUtil.applySha256(tmp);
                            if (hash.equals(this.hashPassword)) {
                                return tmp;
                            }
                        }

                    }

                }

            }
        }
        return null;

    }

}

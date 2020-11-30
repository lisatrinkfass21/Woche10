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
public class FindPassword1 implements Callable<String> {

    String hashPassword;
    int start;
    int end;
    StringBuilder sb = new StringBuilder();

    public FindPassword1(String hashPassword, int start, int end) {
        this.hashPassword = hashPassword;
        this.start = start;
        this.end = end;
    }

    @Override
    public String call() throws Exception {
        if (this.hashPassword != null) {
            String hash;
            String tmp;
            for (int i = 0; i < Beispiel1.großbuchstaben.length; i++) {

                for (int j = 0; j < Beispiel1.großbuchstaben.length; j++) {

                    for (int k = 0; k < Beispiel1.großbuchstaben.length; k++) {

                        for (int l = 0; l < Beispiel1.großbuchstaben.length; l++) {

                            for (int m = 0; m < Beispiel1.großbuchstaben.length; m++) {

                                for (int n = start; n < end; n++) {

                                    tmp = Beispiel1.großbuchstaben[i] + Beispiel1.großbuchstaben[j] + Beispiel1.großbuchstaben[k] + Beispiel1.großbuchstaben[l] + Beispiel1.großbuchstaben[m] + Beispiel1.großbuchstaben[n];

                                    hash = StringUtil.applySha256(tmp);
                                    if (hash.equals(this.hashPassword)) {
                                        return tmp;
                                    }
                                }
                            }
                        }

                    }

                }

            }

        }
        return null;
    }
}

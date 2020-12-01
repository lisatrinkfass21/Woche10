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
public class FindPassword2 implements Callable<String> {

    String hashPassword;
    int start;
    int end;
    StringBuilder sb = new StringBuilder();

    public FindPassword2(String hashPassword, int start, int end) {
        this.hashPassword = hashPassword;
        this.start = start;
        this.end = end;
    }

    @Override
    public String call() throws Exception {
        if (this.hashPassword != null) {
            String hash;
            String tmp;
            /*for (int i = start; i < end; i++) {
                for (int j = 0; j < Beispiel1.klgrza.length; j++) {
                    for (int k = 0; k < Beispiel1.klgrza.length; k++) {
                        for (int l = 0; l < Beispiel1.klgrza.length; l++) {
                            for (int m = 0; m < Beispiel1.klgrza.length; m++) {
                                tmp = Beispiel1.klgrza[i] + Beispiel1.klgrza[j] + Beispiel1.klgrza[k] + Beispiel1.klgrza[l] + Beispiel1.klgrza[m];
                                hash = StringUtil.applySha256(tmp);
                                if (hash.equals(this.hashPassword)) {
                                    return tmp;

                                }
                            }
                        }

                    }

                }

            }

        }*/

            do {
                sb.delete(0, sb.length()); // unbestimmte Zeit
                for (int i = 0; i < 5; i++) {
                    sb.append(Beispiel1.klgrza[(int) (Math.random() * Beispiel1.klgrza.length)]);
                }
                tmp = sb.toString();
                hash = StringUtil.applySha256(tmp);
                if (hash.equals(this.hashPassword)) {
                    Beispiel1.found = true;
                    return tmp;
                }

            } while (Beispiel1.found == false);

        }

        return null;

    }

}

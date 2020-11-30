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

    public FindPassword3(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    @Override
    public String call() throws Exception {
        return null;

    }
}

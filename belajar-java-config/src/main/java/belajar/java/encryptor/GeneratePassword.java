/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.encryptor;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author adi
 */
public class GeneratePassword {
    
    public static void main(String[] args) {
        String key = "passdb123";
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(key);
        
        //Menampilkan encripsi dari password database
        System.out.println(encryptor.encrypt("corsys"));
    }
    
}

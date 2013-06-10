/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.helper;

/**
 *
 * @author adi
 */
public class PagingHelper {
    
    public static Integer getStartOfRow(
            Integer page, Integer baris){
        if(page < 1){
            return 0;
        }
        return (page - 1) * baris;
    }
    
}

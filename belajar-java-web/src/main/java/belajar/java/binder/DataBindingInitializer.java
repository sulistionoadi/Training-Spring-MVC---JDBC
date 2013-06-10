/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.binder;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author adi
 */
public class DataBindingInitializer 
    implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder wdb, WebRequest wr) {
        SimpleDateFormat formatter = 
                new SimpleDateFormat("yyyy-MM-dd");
        wdb.registerCustomEditor(Date.class, 
                new CustomDateEditor(formatter, true));
    }
    
}

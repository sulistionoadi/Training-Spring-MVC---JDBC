/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.controller.validator;

import belajar.java.domain.Customer;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 *
 * @author adi
 */
public class CustomerValidator {
    
    public void validate(Customer customer, Errors error){
        if(!StringUtils.hasText(customer.getNoAccount())){
            error.rejectValue("noAccount", "noAccount.required",
                    "No Account Harus diisi !!");
        }
    }
    
}

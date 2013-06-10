/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.controller;

import belajar.java.controller.validator.CustomerValidator;
import belajar.java.domain.Customer;
import belajar.java.service.AplikasiService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author adi
 */

@Controller
public class CustomerController {
    private static final Log logger = 
            LogFactory.getLog(CustomerController.class);
    @Autowired
    private AplikasiService aplikasiService;
    
    @RequestMapping(value="/customer/form", 
            method= RequestMethod.GET)
    public ModelMap tampilFormCustomer(
            @RequestParam(value="id", required=false) String id,
            @RequestParam(value="account", required=false) String account){
        logger.info("INI CONTROLLER /CUSTOMER/FORM");
        
        ModelMap mm = new ModelMap();
        Customer c = new Customer();
        if(StringUtils.hasText(id)){
            c = aplikasiService.cariCustomerById(id);
        } else if(StringUtils.hasText(account)){
            c = aplikasiService.cariCustomerByNoAccount(account);
            
        }
        return mm.addAttribute("customer", c);
    }
    
    @RequestMapping(value="/customer/form", 
            method= RequestMethod.POST)
    public String simpanCustomer(
            @ModelAttribute Customer customer,
            BindingResult bindingResult){
        logger.info("INI CONTROLLER SIMPAN CUSTOMER");
        new CustomerValidator().validate(customer, bindingResult);
        
        if(bindingResult.hasErrors()){
            return "redirect:form";
        }
//            customer.setFirstname(customer.getFirstname().toUpperCase());
//            customer.setLastname(customer.getLastname().toUpperCase());

//            Customer custDb = 
//                    aplikasiService.cariCustomerById(id);
//            if(custDb!=null){
//                customer.setIdNumber(custDb.getIdNumber());
//                aplikasiService.edit(customer);
//            } else {
                aplikasiService.simpan(customer);
//            }
            return "redirect:data";
    }
    
    @RequestMapping("/customer/data")
    public ModelMap tampilDataCustomer(){
        logger.info("INI CONTROLLER /CUSTOMER/FORM");
        List<Customer> customers = 
                aplikasiService.cariSemuaCustomer(1, 20);
        return new ModelMap("listCustomer",customers);
    }
    
    @RequestMapping(value="/customer/delete",
            method= RequestMethod.GET)
    public String deleteCustomer(
            @RequestParam(value="id", required=true) String id){
        
        Customer c = aplikasiService.cariCustomerById(id);
        if(c!=null){
            aplikasiService.delete(c);
        } else {
            logger.warn("Customer dengan id [" + id + "] "
                    + "tidak ditemukan");
        }
        
        return "redirect:data";
    }
    
}

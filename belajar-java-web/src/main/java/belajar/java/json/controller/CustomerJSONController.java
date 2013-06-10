/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.json.controller;

import belajar.java.domain.Customer;
import belajar.java.domain.CustomerList;
import belajar.java.service.AplikasiService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author adi
 */


@Controller
public class CustomerJSONController {
    
    private static final Log logger = 
            LogFactory.getLog(CustomerJSONController.class);
    @Autowired
    private AplikasiService aplikasiService;
    
    @RequestMapping(value="/customer/list", headers="Accept=application/xml")
    @ResponseBody
    public CustomerList listXmlCustomers(){
        List<Customer> data = aplikasiService.cariSemuaCustomer(1, 20);
        CustomerList cl = new CustomerList();
        cl.setCount(data.size());
        cl.setCustomerList(data);
        
        return cl;
    }
    
    @RequestMapping(value="/customer/list", headers="Accept=application/json")
    @ResponseBody
    public List<Customer> listJsonCustomers(){
        return aplikasiService.cariSemuaCustomer(1, 20);
    }
    
    @RequestMapping(value="/customer/{id}",headers="Accept=application/xml, application/json")
    @ResponseBody
    public Customer xmlFindCustomerById(@PathVariable String id){
        return aplikasiService.cariCustomerById(id);
    }
    
    @RequestMapping("/string/array")
    @ResponseBody
    public String[] responseArrayOfString(){
        String[] result = {"satu","dua","tiga"};
        return result;
    }
    
    @RequestMapping(value="/customer/", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer, HttpServletRequest request, HttpServletResponse response){
        aplikasiService.simpan(customer);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, customer.getIdNumber());
        response.setHeader("Location", uri.toASCIIString());
    }
    
}

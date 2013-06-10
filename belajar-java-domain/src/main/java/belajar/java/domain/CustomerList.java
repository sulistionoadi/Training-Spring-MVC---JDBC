/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adi
 */

@XmlRootElement(name="customers")
public class CustomerList {
    
    private int count;
    private List<Customer> customerList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    
}

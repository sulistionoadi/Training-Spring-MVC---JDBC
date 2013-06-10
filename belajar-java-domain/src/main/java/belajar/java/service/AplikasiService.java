/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.service;

import belajar.java.domain.Customer;
import java.util.List;

/**
 *
 * @author adi
 */
public interface AplikasiService {
    
    public void simpan(Customer customer);
    public void simpan(List<Customer> customers);
    public void edit(Customer customer);
    public void delete(Customer customer);
    public Customer cariCustomerById(String id);
    public Customer cariCustomerByNoAccount(String noAccount);
    public Long hitungSemuaCustomer();
    public List<Customer> cariSemuaCustomer(
            Integer page, Integer baris);
    
}

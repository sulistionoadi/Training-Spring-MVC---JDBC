/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.service.impl;

import belajar.java.service.dao.CustomerDao;
import belajar.java.domain.Customer;
import belajar.java.service.AplikasiService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("aplikasiService")
@Transactional
public class AplikasiServiceImpl implements AplikasiService{
    
    private static Log logger = 
            LogFactory.getLog(AplikasiServiceImpl.class);
    
    @Autowired private CustomerDao customerDao;

    public void simpan(Customer customer) {
        customerDao.simpan(customer);
        //updateSaldo();
        //insertJurnal();
    }
    
    public void simpan(List<Customer> customers) {
        for (Customer c : customers) {
            customerDao.simpan(c);
            logger.info("ID NUMBER Tersimpan: " 
                    + c.getIdNumber());
        }
    }

    public void edit(Customer customer) {
        customerDao.edit(customer);
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    public Customer cariCustomerById(String id) {
        return customerDao.cariCustomerById(id);
    }

    public Customer cariCustomerByNoAccount(String noAccount) {
        return customerDao.cariCustomerByNoAccount(noAccount);
    }
    
    public Long hitungSemuaCustomer(){
        return customerDao.hitungSemuaCustomer();
    }

    public List<Customer> cariSemuaCustomer(
            Integer page, Integer baris) {
        return customerDao.cariSemuaCustomer(page, baris);
    }
    
}

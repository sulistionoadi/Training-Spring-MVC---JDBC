/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.service.test;

import belajar.java.domain.Customer;
import belajar.java.service.AplikasiService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author adi
 */
public class CustomerServiceTest {

    private static AbstractApplicationContext ctx;
    private static AplikasiService appService;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath*:belajar/java/**/applicationContext.xml");
        appService = (AplikasiService) ctx.getBean("aplikasiService");
    }

    @Test
    public void testSimpanCustomer() {
        try{
            String idNumber = "CUST01";
            List<Customer> customers = new ArrayList<Customer>();
            for(int i=1; i<=5; i++){
                Customer c = new Customer();
                c.setIdNumber(idNumber + i);
                c.setFirstname("ANGGI" + i);
                c.setNoAccount("0012984" + i);
                c.setLastname("RARA");
                c.setBirthday(new Date());
                customers.add(c);
            }
            appService.simpan(customers);
        } catch(DataIntegrityViolationException ex){
            System.out.println("Duplikat Entry !! ");
            System.out.println(ex.getCause());
        } catch(Exception ex){
            
        }
    }

//    @Test
    public void testFindALl() {
        List<Customer> customers = 
                appService.cariSemuaCustomer(1, 10);
        for (Customer c : customers) {
            System.out.println("********************************");
            tampilObject(c);
        }
    }

//    @Test
    public void testDeleteCustomer() {
        Customer c = appService.cariCustomerById("CUST011");
        tampilObject(c);
        System.out.println("MENGHAPUS FILE DENGAN ID :" 
                + c.getIdNumber());
        appService.delete(c);
    }

    private void tampilObject(Customer customer) {
        System.out.println("ID_NUMBER [" + customer.getIdNumber() + "]");
        System.out.println("FIRSTNAME [" + customer.getFirstname() + "]");
        System.out.println("LASTNAME [" + customer.getLastname() + "]");
        System.out.println("BIRTHDAY [" + customer.getBirthday() + "]");
        System.out.println("NO_ACCOUNT [" + customer.getNoAccount() + "]");
        System.out.println("SALDO [" + customer.getSaldo() + "]");
    }

//    @Test
    public void testEditCustomer() {
        Customer c = appService.cariCustomerById("CUST101");
        tampilObject(c);
        c.setLastname("BIMA FATKHAN");
        System.out.println("MENGEDIT FILE DENGAN ID :" 
                + c.getIdNumber());
        appService.edit(c);
    }
}

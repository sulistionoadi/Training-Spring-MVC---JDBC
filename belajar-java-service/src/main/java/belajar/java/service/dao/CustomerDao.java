/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.service.dao;

import belajar.java.domain.Customer;
import belajar.java.helper.PagingHelper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adi
 */

@Repository
public class CustomerDao {
    
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = 
                new NamedParameterJdbcTemplate(dataSource);
    }
    
    public void simpan(Customer customer){
        String insertCustomer = 
                "INSERT INTO CUSTOMER ( "
                + "ID_NUMBER, FIRSTNAME, LASTNAME, BIRTHDAY, "
                + "ACCOUNT_NO, SALDO) VALUES (:idNumber, :firstname, "
                + ":lastname, :birthday, :noAccount, :saldo)";
                
        SqlParameterSource namedParameter
                = new BeanPropertySqlParameterSource(customer);
        namedParameterJdbcTemplate
                .update(insertCustomer, namedParameter);
    }
    
    public void delete(Customer customer){
    	String deleteCustomer = 
                "DELETE FROM CUSTOMER WHERE ID_NUMBER = :idNumber";
    	SqlParameterSource namedParameter
        	= new BeanPropertySqlParameterSource(customer);
    	namedParameterJdbcTemplate
        	.update(deleteCustomer, namedParameter);
    }
    
    public void edit(Customer customer){
    	String editCustomer = 
               "UPDATE CUSTOMER SET FIRSTNAME=:firstname, "
               + "LASTNAME=:lastname, BIRTHDAY=:birthday, "
               + "ACCOUNT_NO=:noAccount, SALDO=:saldo WHERE ID_NUMBER=:idNumber";
    	SqlParameterSource namedParameter
        	= new BeanPropertySqlParameterSource(customer);
    	namedParameterJdbcTemplate
        	.update(editCustomer, namedParameter);
    }
    
    public Customer cariCustomerById(String id){
        try{
            String cariById = "SELECT * FROM CUSTOMER "
                    + "WHERE ID_NUMBER = ? ";
            return jdbcTemplate.queryForObject(cariById, 
                    new ResultSetCustomer(), id);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    public Customer cariCustomerByNoAccount(String noAccount){
        try {
            String cariByNoAccount = "SELECT * FROM CUSTOMER "
                    + "WHERE ACCOUNT_NO = ? ";
            return jdbcTemplate.queryForObject(cariByNoAccount, 
                    new ResultSetCustomer(), noAccount);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    public Long hitungSemuaCustomer(){
        String countCustomer = "SELECT COUNT(*) FROM CUSTOMER";
        return jdbcTemplate.queryForLong(countCustomer);
    }
    
    public List<Customer> cariSemuaCustomer(
            Integer page, Integer baris){
    	String cariSemua = "SELECT * FROM CUSTOMER LIMIT ?,?";
    	return jdbcTemplate.query(cariSemua, 
                new ResultSetCustomer(), 
                PagingHelper.getStartOfRow(page,baris),
                baris);
    }
    
    private class ResultSetCustomer 
        implements RowMapper<Customer> {
        
        public Customer mapRow(ResultSet rs, int i) 
                throws SQLException {
            Customer c = new Customer();
            c.setBirthday(rs.getDate("BIRTHDAY"));
            c.setFirstname(rs.getString("FIRSTNAME"));
            c.setLastname(rs.getString("LASTNAME"));
            c.setIdNumber(rs.getString("ID_NUMBER"));
            c.setNoAccount(rs.getString("ACCOUNT_NO"));
            c.setSaldo(rs.getBigDecimal("SALDO"));
            return c;
        }
    }
}


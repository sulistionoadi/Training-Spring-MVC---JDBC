/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.domain;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author adi
 */

//@XmlRootElement(name="customer")
public class Customer extends Person {
    
    @NotNull @NotEmpty
    @Length(min=8,max=13)
    private String noAccount;
    
    @NotNull @NotEmpty
    private String idNumber;
    
    @Min(0) @NotNull
    private BigDecimal saldo = BigDecimal.ZERO;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNoAccount() {
        return noAccount;
    }

    public void setNoAccount(String noAccount) {
        this.noAccount = noAccount;
    }
    
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
}

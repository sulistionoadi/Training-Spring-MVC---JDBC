/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package belajar.java.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;


/**
 *
 * @author adi
 */
public class Person {
    
    private String firstname;
    private String lastname;
    private Date birthday;

    public Person(){
        
    }
    
    public Person(String firstname, String lastname, Date birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        if(birthday==null){
            return birthday;
        } else {
            DateTime dateWithoutTime = 
                    new DateTime(birthday.getTime());
            dateWithoutTime.withHourOfDay(0);
            dateWithoutTime.withMinuteOfHour(0);
            dateWithoutTime.withSecondOfMinute(0);
            dateWithoutTime.withMillisOfSecond(0);

            return dateWithoutTime.toDate();
        }
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
}

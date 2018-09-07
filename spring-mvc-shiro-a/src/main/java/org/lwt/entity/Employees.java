package org.lwt.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`employees`")
public class Employees {
    @Id
    @Column(name = "`emp_no`")
    private Integer empNo;

    @Column(name = "`birth_date`")
    private Date birthDate;

    @Column(name = "`first_name`")
    private String firstName;

    @Column(name = "`last_name`")
    private String lastName;

    @Column(name = "`gender`")
    private String gender;

    @Column(name = "`hire_date`")
    private Date hireDate;

    /**
     * @return emp_no
     */
    public Integer getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo
     */
    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    /**
     * @return birth_date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return first_name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * @return last_name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return hire_date
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
package org.lwt.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"firstName","role","snakes"})
@XmlRootElement(name = "person")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Person implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private String role;
  private List<Snake> snakes;
  
  
  @XmlAttribute
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  @XmlAttribute     // ÊôÐÔ
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  @XmlElement       // ÔªËØ
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  
  @XmlElementWrapper(name = "pet")
  @XmlElement
  public List<Snake> getSnakes() {
    return snakes;
  }
  public void setSnakes(List<Snake> snakes) {
    this.snakes = snakes;
  }
}

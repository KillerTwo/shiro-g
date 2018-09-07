package org.lwt.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "snake")
public class Snake {
  private String name;
  private String species;
  private List<Snake> child;
  @XmlAttribute
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @XmlAttribute
  public String getSpecies() {
    return species;
  }
  public void setSpecies(String species) {
    this.species = species;
  }
  @XmlElement
  public List<Snake> getChild() {
    return child;
  }
  public void setChild(List<Snake> child) {
    this.child = child;
  }
  
}

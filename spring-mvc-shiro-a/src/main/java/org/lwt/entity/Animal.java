package org.lwt.entity;

public class Animal {
  private String name;
  private String species;
  private int num;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSpecies() {
    return species;
  }
  public void setSpecies(String species) {
    this.species = species;
  }
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  @Override
  public String toString() {
    return "Animal [name=" + name + ", species=" + species + ", num=" + num + "]";
  }
  
}

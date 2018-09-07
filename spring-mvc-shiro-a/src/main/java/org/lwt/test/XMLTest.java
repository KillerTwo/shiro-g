package org.lwt.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.lwt.entity.Person;
import org.lwt.entity.Snake;

public class XMLTest {
  public static void main(String[] args){
    
    String path = XMLTest.class.getClassLoader().getResource("").getPath();
    path = path.substring(1, path.length());
    
    List<Snake> child = new ArrayList<>();
    Snake snake6 = new Snake();
    snake6.setName("太攀蛇");
    snake6.setSpecies("cobra");
    child.add(snake6);
    
    Snake snake7 = new Snake();
    snake7.setName("变色蛇");
    snake7.setSpecies("cobra");
    child.add(snake7);
    
    Snake snake8 = new Snake();
    snake8.setName("绿瘦蛇");
    snake8.setSpecies("cobra");
    child.add(snake8);
    
    
    Snake snake = new Snake();
    snake.setName("眼镜蛇");
    snake.setSpecies("cobra");
    List<Snake> list = new ArrayList<>();
    list.add(snake);
    
    Snake snake2 = new Snake();
    snake2.setName("银环蛇");
    snake2.setSpecies("cobra");
    snake2.setChild(child);
    list.add(snake2);
    
    Snake snake3 = new Snake();
    snake3.setName("金环蛇");
    snake3.setSpecies("cobra");
    list.add(snake3);
    
    Snake snake4 = new Snake();
    snake4.setName("喜玛拉雅白头蛇");
    snake4.setSpecies("cobra");
    list.add(snake4);
    
    Person person = new Person();
    person.setFirstName("欧阳");
    person.setLastName("alice");
    person.setRole("admin");
    person.setSnakes(list);
    
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(Person.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      // 自动换行
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(person,System.out);
      // 输出到指定的文件
      marshaller.marshal(person, new File(path+"test003.xml"));
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }
}

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
    snake6.setName("̫����");
    snake6.setSpecies("cobra");
    child.add(snake6);
    
    Snake snake7 = new Snake();
    snake7.setName("��ɫ��");
    snake7.setSpecies("cobra");
    child.add(snake7);
    
    Snake snake8 = new Snake();
    snake8.setName("������");
    snake8.setSpecies("cobra");
    child.add(snake8);
    
    
    Snake snake = new Snake();
    snake.setName("�۾���");
    snake.setSpecies("cobra");
    List<Snake> list = new ArrayList<>();
    list.add(snake);
    
    Snake snake2 = new Snake();
    snake2.setName("������");
    snake2.setSpecies("cobra");
    snake2.setChild(child);
    list.add(snake2);
    
    Snake snake3 = new Snake();
    snake3.setName("����");
    snake3.setSpecies("cobra");
    list.add(snake3);
    
    Snake snake4 = new Snake();
    snake4.setName("ϲ�����Ű�ͷ��");
    snake4.setSpecies("cobra");
    list.add(snake4);
    
    Person person = new Person();
    person.setFirstName("ŷ��");
    person.setLastName("alice");
    person.setRole("admin");
    person.setSnakes(list);
    
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(Person.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      // �Զ�����
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(person,System.out);
      // �����ָ�����ļ�
      marshaller.marshal(person, new File(path+"test003.xml"));
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }
}

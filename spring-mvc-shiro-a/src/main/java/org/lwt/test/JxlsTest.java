package org.lwt.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.lwt.entity.Animal;
import org.lwt.entity.Employees;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

public class JxlsTest {
  public static void main(String[] args) {
    /*List<Animal> employees = generateSampleEmployeeData();
    System.out.println(employees);
    try(InputStream is = new FileInputStream(new File("C:\\Users\\Administrator\\Documents\\test01\\my_template.xls"));
        OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Documents\\test01\\my_out_result.xls")) {
        Context context = new Context();
        context.putVar("employees", employees);
        JxlsHelper.getInstance().processTemplate(is, os, context);
    }catch(Exception e) {
      e.printStackTrace();
    }*/
    JxlsTest jxl = new JxlsTest();
    List<Animal> employees = jxl.getDataForExcel("C:\\Users\\Administrator\\Documents\\test01\\test00001.xlsx");
    System.out.println("����������ǣ�");
    if(employees != null && employees.size() > 0) {
      for (Animal animal : employees) {
        System.out.println(animal);
      }
    }
  }

  private static List<Animal> generateSampleEmployeeData() {
    List<Animal> list = new ArrayList<>();
    for(int i = 0; i < 6; i++) {
      Animal animal = new Animal();
      animal.setName("����"+i*100);
      animal.setSpecies("��������"+i*100);
      animal.setNum(i*10);
      list.add(animal);
    }
    return list;
  }
  /**
   * 
   * @param path �����ļ�
   * @return  List<Animal> �������ļ���ȡ������
   */
  public List<Animal> getDataForExcel(String path){
    List<Animal> employees = new ArrayList<>();
    // ��ȡ�����ļ�
    try {
      InputStream inputXML = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("jxls/imrconfig.xml"));
      XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
      // �ļ�������
      InputStream inputXLS = new BufferedInputStream(new FileInputStream(new File(path)));
      Animal animal = new Animal();
      Map<String, Object> map = new HashMap<>();
      map.put("employees", employees);
      map.put("employee", animal);
      XLSReadStatus readStatus = mainReader.read( inputXLS, map);
      if(readStatus.isStatusOK()){
        System.out.println("jxls��ȡExcel�ɹ���");
      }
    }catch(Exception e) {
      e.printStackTrace();
    }
    
    return employees;
  }
}
